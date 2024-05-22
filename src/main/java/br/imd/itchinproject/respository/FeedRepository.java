package br.imd.itchinproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.imd.itchinproject.entity.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {
	
	@Query("SELECT f FROM Feed f WHERE f.id = :userId")
    Feed findByUsuarioId(@Param("userId") Long userId);
}
