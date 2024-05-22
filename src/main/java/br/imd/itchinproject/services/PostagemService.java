package br.imd.itchinproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.imd.itchinproject.entity.Feed;
import br.imd.itchinproject.entity.Postagem;
import br.imd.itchinproject.entity.Usuario;
import br.imd.itchinproject.respository.PostagemRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class PostagemService {

    private static final Logger logger = LoggerFactory.getLogger(PostagemService.class);

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private FeedService feedService;
    
    @Autowired
    private UsuarioService usuarioService;

    public List<Postagem> findAll() {
        return postagemRepository.findAll();
    }

    public Postagem findById(Long id) {
        return postagemRepository.findById(id).orElse(null);
    }

    @Transactional
    public Postagem save(Postagem postagem) {
        logger.info("Salvando a postagem: {}", postagem);

        Postagem savedPostagem = postagemRepository.save(postagem);
        logger.info("Postagem salva com sucesso: {}", savedPostagem);

        // Adicionar o ID da postagem à lista de postagens do usuário
        Usuario usuario = usuarioService.findById(savedPostagem.getUserId());
        if (usuario != null) {
            usuario.addPost(savedPostagem.getId());
            usuarioService.save(usuario);
        }

        // Atualizar os feeds
        updateFeeds(savedPostagem, usuario);

        return savedPostagem;
    }

    @Transactional
    public void deleteById(Long id) {
        Postagem postagem = postagemRepository.findById(id).orElse(null);
        if (postagem != null) {
            // Remover a postagem dos feeds dos usuários
            Usuario usuario = usuarioService.findById(postagem.getUserId());
            if (usuario != null) {
                usuario.removePost(postagem.getId());
                usuarioService.save(usuario);
                updateFeeds(postagem, usuario);
            }

            // Excluir a postagem
            postagemRepository.deleteById(id);
        }
    }
    
    @Transactional
    public void deleteByUserId(Long userId) {
        // Encontra todas as postagens associadas ao usuário pelo id do usuário
        List<Postagem> postagensDoUsuario = postagemRepository.findByUserId(userId);
        
        // Para cada postagem associada ao usuário
        for (Postagem postagem : postagensDoUsuario) {
            // Remove a postagem da lista de postagens do usuário
            Usuario usuario = usuarioService.findById(userId);
            if (usuario != null) {
                usuario.removePost(postagem.getId());
                usuarioService.save(usuario);
            }

            // Exclui a postagem
            postagemRepository.deleteById(postagem.getId());
        }
    }

    @Transactional
    public void increaseLikes(Long postId) {
        Postagem postagem = postagemRepository.findById(postId).orElse(null);
        if (postagem != null) {
            postagem.setLikes(postagem.getLikes() + 1);
            postagemRepository.save(postagem);
        }
    }

    private void updateFeeds(Postagem postagem, Usuario usuario) {
        if (usuario != null) {
            Feed userFeed = feedService.findByUserId(usuario.getId());
            if (userFeed != null) {
                userFeed.addPostagem(postagem);
                feedService.save(userFeed);
            }

            List<Long> seguidores = usuario.getFollowers();
            for (Long seguidorId : seguidores) {
                Feed feedSeguidor = feedService.findByUserId(seguidorId);
                if (feedSeguidor != null) {
                    feedSeguidor.addPostagem(postagem);
                    feedService.save(feedSeguidor);
                }
            }
        }
    }
}
