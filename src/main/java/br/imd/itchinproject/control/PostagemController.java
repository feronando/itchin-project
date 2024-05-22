package br.imd.itchinproject.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.imd.itchinproject.entity.Postagem;
import br.imd.itchinproject.services.PostagemService;

import java.util.List;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @GetMapping
    public ResponseEntity<List<Postagem>> findAll() {
        List<Postagem> postagens = postagemService.findAll();
        return new ResponseEntity<>(postagens, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> findById(@PathVariable Long id) {
        Postagem postagem = postagemService.findById(id);
        if (postagem != null) {
            return new ResponseEntity<>(postagem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Postagem> save(@RequestBody Postagem postagem) {
        Postagem savedPostagem = postagemService.save(postagem);
        return new ResponseEntity<>(savedPostagem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        postagemService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/like/{id}")
    public ResponseEntity<Void> increaseLikes(@PathVariable Long id) {
        postagemService.increaseLikes(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
