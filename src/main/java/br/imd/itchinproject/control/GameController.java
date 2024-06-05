package br.imd.itchinproject.control;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.imd.itchinproject.entity.Game;
import br.imd.itchinproject.respository.GameRepository;

@Controller
public class GameController {

    private static String UPLOAD_DIR = "uploads/";

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String uploadGame(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/";
        }

        try {
            // Save the uploaded file
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, bytes);

            // Unzip the file and check for index.html
            boolean containsIndexHtml = false;
            Path unzipDir = Paths.get(UPLOAD_DIR, name);
            Files.createDirectories(unzipDir);

            // Explicitly specify the charset for ZipInputStream
            try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(file.getBytes()), StandardCharsets.ISO_8859_1)) {
                ZipEntry zipEntry;
                while ((zipEntry = zis.getNextEntry()) != null) {
                    Path newFile = unzipDir.resolve(zipEntry.getName());
                    if (zipEntry.isDirectory()) {
                        Files.createDirectories(newFile);
                    } else {
                        // Write file (overwriting if it already exists)
                        Files.copy(zis, newFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                        if (zipEntry.getName().equals("index.html")) {
                            containsIndexHtml = true;
                        }
                    }
                    zis.closeEntry();
                }
            }

            Game game = new Game();
            game.setName(name);
            game.setFileName(file.getOriginalFilename());
            gameRepository.save(game);

            redirectAttributes.addFlashAttribute("message", "Game uploaded successfully: " + name);
            redirectAttributes.addFlashAttribute("link", "/game/" + name);
            if (containsIndexHtml) {
                redirectAttributes.addFlashAttribute("isHtml", true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @GetMapping("/game/{name}")
    public String getGame(@PathVariable String name, Model model) {
        Game game = gameRepository.findByName(name);
        if (game != null) {
            model.addAttribute("game", game);
            Path gameDir = Paths.get(UPLOAD_DIR, name);
            if (Files.exists(gameDir.resolve("index.html"))) {
                model.addAttribute("isHtml", true);
            }
        } else {
            model.addAttribute("message", "Game not found");
        }
        return "game";
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadGame(@PathVariable String fileName) {
        Path file = Paths.get(UPLOAD_DIR, fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(file.toUri());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(MaxUploadSizeExceededException exc, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "File too large!");
        return "redirect:/";
    }
}
