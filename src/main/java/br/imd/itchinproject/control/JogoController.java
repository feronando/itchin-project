package br.imd.itchinproject.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.imd.itchinproject.entity.Jogo;
import br.imd.itchinproject.services.JogoService;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @GetMapping
    public List<Jogo> findAll() {
        return jogoService.findAll();
    }

    @GetMapping("/{id}")
    public Jogo findById(@PathVariable Long id) {
        return jogoService.findById(id);
    }

    @PostMapping
    public Jogo save(@RequestBody Jogo jogo) {
        return jogoService.save(jogo);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        jogoService.deleteById(id);
    }
}
