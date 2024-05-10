package br.imd.itchinproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.imd.itchinproject.entity.Seguir;
import br.imd.itchinproject.respository.SeguirRepository;

import java.util.List;

@Service
public class SeguirService {

    @Autowired
    private SeguirRepository seguirRepository;

    public List<Seguir> findAll() {
        return seguirRepository.findAll();
    }

    public Seguir findById(Long id) {
        return seguirRepository.findById(id).orElse(null);
    }

    public Seguir save(Seguir seguir) {
        return seguirRepository.save(seguir);
    }

    public void deleteById(Long id) {
        seguirRepository.deleteById(id);
    }
}
