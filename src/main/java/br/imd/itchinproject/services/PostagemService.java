package br.imd.itchinproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.imd.itchinproject.entity.Feed;
import br.imd.itchinproject.entity.Postagem;
import br.imd.itchinproject.entity.Usuario;
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

    //Terminar
    /*public Postagem save(Postagem postagem) {
    	Postagem savedPostagem = postagemRepository.save(postagem);
        
        // Atualizar o feed dos seguidores
        Usuario usuario = savedPostagem.getUser();
        List<Usuario> seguidores = usuario.getFollowers();
        for (Usuario seguidor : seguidores) {
            Feed feed = seguidor.getFeed();
            if (feed == null) {
                feed = new Feed();
                feed.setUsuario(seguidor);
                feed.setPostagens(new ArrayList<>());
            }
            feed.getPostagens().add(savedPostagem);
            //FeedRepository.save(feed);
        }
        
        return savedPostagem;
    }*/

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
