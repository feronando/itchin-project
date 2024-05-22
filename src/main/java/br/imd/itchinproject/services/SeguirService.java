package br.imd.itchinproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.imd.itchinproject.entity.Seguir;
import br.imd.itchinproject.entity.Usuario;
import br.imd.itchinproject.respository.SeguirRepository;
import br.imd.itchinproject.respository.UsuarioRepository;

import java.util.List;

@Service
public class SeguirService {

    @Autowired
    private SeguirRepository seguirRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    public List<Long> findSeguidoresByIdUsuario(Long idUsuario) {
        return seguirRepository.findSeguidoresByIdUsuario(idUsuario);
    }

    public List<Long> findSeguindoByIdUsuario(Long idUsuario) {
        return seguirRepository.findSeguindoByIdUsuario(idUsuario);
    }

    public List<Seguir> findAll() {
        return seguirRepository.findAll();
    }

    public Seguir findById(Long id) {
        return seguirRepository.findById(id).orElse(null);
    }

    public Seguir save(Seguir seguir) {
        Usuario follower = usuarioService.findById(seguir.getFollowerId());
        Usuario following = usuarioService.findById(seguir.getFollowingId());
        if (follower != null && following != null) {
            follower.addFollowing(following.getId());
            following.addFollower(follower.getId());
            usuarioService.save(follower);
            usuarioService.save(following);
            return seguirRepository.save(seguir);
        }
        return null;
    }

    public void deleteById(Long id) {
        Seguir seguir = seguirRepository.findById(id).orElse(null);
        if (seguir != null) {
            Usuario follower = usuarioService.findById(seguir.getFollowerId());
            Usuario following = usuarioService.findById(seguir.getFollowingId());
            if (follower != null && following != null) {
                follower.removeFollowing(following.getId());
                following.removeFollower(follower.getId());
                usuarioService.save(follower);
                usuarioService.save(following);
                seguirRepository.deleteById(id);
            }
        }
    }
}