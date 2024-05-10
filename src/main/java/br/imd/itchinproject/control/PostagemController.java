package br.imd.itchinproject.control;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Postagem> findAll() {
        return postagemService.findAll();
    }

    @GetMapping("/{id}")
    public Postagem findById(@PathVariable Long id) {
        return postagemService.findById(id);
    }

    @PostMapping
    public Postagem save(@RequestBody Postagem postagem) {
        return postagemService.save(postagem);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        postagemService.deleteById(id);
    }
}
