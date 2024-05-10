package br.imd.itchinproject.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.imd.itchinproject.entity.Seguir;
import br.imd.itchinproject.services.SeguirService;

import java.util.List;

@RestController
@RequestMapping("/seguir")
public class SeguirController {

    @Autowired
    private SeguirService seguirService;

    @GetMapping
    public List<Seguir> findAll() {
        return seguirService.findAll();
    }

    @GetMapping("/{id}")
    public Seguir findById(@PathVariable Long id) {
        return seguirService.findById(id);
    }

    @PostMapping
    public Seguir save(@RequestBody Seguir seguir) {
        return seguirService.save(seguir);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        seguirService.deleteById(id);
    }
}
