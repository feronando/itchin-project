package br.imd.itchinproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.imd.itchinproject.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByName(String name);
}
