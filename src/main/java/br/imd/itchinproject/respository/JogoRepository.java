package br.imd.itchinproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.imd.itchinproject.entity.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
}
