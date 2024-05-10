package br.imd.itchinproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.imd.itchinproject.entity.Postagem;
import br.imd.itchinproject.respository.PostagemRepository;

import java.util.List;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    public List<Postagem> findAll() {
        return postagemRepository.findAll();
    }

    public Postagem findById(Long id) {
        return postagemRepository.findById(id).orElse(null);
    }

    public Postagem save(Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    public void deleteById(Long id) {
        postagemRepository.deleteById(id);
    }
    
    public void increaseLikes(Long postId) {
        Postagem postagem = postagemRepository.findById(postId).orElse(null);
        if (postagem != null) {
            postagem.setLikes(postagem.getLikes() + 1);
            postagemRepository.save(postagem);
        }
    }
}
