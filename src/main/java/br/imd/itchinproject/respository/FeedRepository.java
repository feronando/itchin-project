package br.imd.itchinproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.imd.itchinproject.entity.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
