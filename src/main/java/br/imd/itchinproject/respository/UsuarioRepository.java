package br.imd.itchinproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.imd.itchinproject.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}