package br.imd.itchinproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.imd.itchinproject.entity.Feed;
import br.imd.itchinproject.entity.Usuario;
import br.imd.itchinproject.respository.FeedRepository;
import br.imd.itchinproject.respository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private FeedService feedService;
    
    //@Autowired
    //private PostagemService postagemService;

    @Transactional
    public Usuario createUsuario(Usuario usuario) {
        // Salvar o usuário
        Usuario savedUsuario = usuarioRepository.save(usuario);

        // Criar o feed associado
        Feed newFeed = new Feed();
        newFeed.setUsuarioId(savedUsuario.getId());
        newFeed.setPostagens(new ArrayList<>());
        Feed savedFeed = feedService.save(newFeed);

        // Atualizar o usuário com o ID do feed
        savedUsuario.setFeedId(savedFeed.getId());

        return usuarioRepository.save(savedUsuario);
    }
    
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            // Remover o usuário dos seguidores de outros usuários
            List<Long> followers = usuario.getFollowers();
            if (followers != null) {
                for (Long followerId : followers) {
                    Usuario follower = usuarioRepository.findById(followerId).orElse(null);
                    if (follower != null) {
                        follower.removeFollowing(id);
                        usuarioRepository.save(follower);
                    }
                }
            }

            // Remover o usuário dos usuários seguidos por outros usuários
            List<Long> following = usuario.getFollowing();
            if (following != null) {
                for (Long followingId : following) {
                    Usuario followed = usuarioRepository.findById(followingId).orElse(null);
                    if (followed != null) {
                        followed.removeFollower(id);
                        usuarioRepository.save(followed);
                    }
                }
            }
            
            // Excluir o feed associado ao usuário
            Long feedId = usuario.getFeedId();
            if (feedId != null) {
            	feedService.deleteById(feedId);
            }
            
            // Excluir as postagens do usuário
            /*List<Long> postagens = usuario.getPosts();
            if (postagens != null) {
                for (Long postId : postagens) {
                    postagemService.deleteById(postId); // Agora o serviço de postagem é chamado aqui
                }
            }*/

            // Excluir o usuário
            usuarioRepository.deleteById(id);
        }
    }
}
