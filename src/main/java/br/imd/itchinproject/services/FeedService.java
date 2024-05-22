package br.imd.itchinproject.services;

import br.imd.itchinproject.entity.Feed;
import br.imd.itchinproject.respository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {

    @Autowired
    private FeedRepository feedRepository;

    public Feed findByUserId(Long userId) {
        return feedRepository.findByUsuarioId(userId);
    }

    public Feed save(Feed feed) {
        return feedRepository.save(feed);
    }

    public Feed update(Feed feed) {
        if (feed.getUsuarioId() == null) {
            throw new IllegalArgumentException("Feed ID cannot be null");
        }
        return feedRepository.save(feed);
    }

    public void deleteById(Long id) {
        feedRepository.deleteById(id);
    }

    public List<Feed> findAll() {
        return feedRepository.findAll();
    }

    public Feed findById(Long id) {
        return feedRepository.findById(id).orElse(null);
    }
}
