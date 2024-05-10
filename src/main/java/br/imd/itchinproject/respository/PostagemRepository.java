package br.imd.itchinproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.imd.itchinproject.entity.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
}
