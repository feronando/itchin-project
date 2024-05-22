package br.imd.itchinproject.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.imd.itchinproject.entity.Seguir;

public interface SeguirRepository extends JpaRepository<Seguir, Long> {
	@Query("SELECT s.followerId FROM Seguir s WHERE s.followingId = :idUsuario")
    List<Long> findSeguidoresByIdUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT s.followingId FROM Seguir s WHERE s.followerId = :idUsuario")
    List<Long> findSeguindoByIdUsuario(@Param("idUsuario") Long idUsuario);
}

