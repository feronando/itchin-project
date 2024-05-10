package br.imd.itchinproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.imd.itchinproject.entity.Jogo;
import br.imd.itchinproject.respository.JogoRepository;

import java.util.List;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    public List<Jogo> findAll() {
        return jogoRepository.findAll();
    }

    public Jogo findById(Long id) {
        return jogoRepository.findById(id).orElse(null);
    }

    public Jogo save(Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    public void deleteById(Long id) {
        jogoRepository.deleteById(id);
    }
}
