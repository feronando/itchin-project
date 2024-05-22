package br.imd.itchinproject.control;

import br.imd.itchinproject.entity.Feed;
import br.imd.itchinproject.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping
    public List<Feed> findAll() {
        return feedService.findAll();
    }

    @GetMapping("/{id}")
    public Feed findById(@PathVariable Long id) {
        return feedService.findById(id);
    }
    
    @GetMapping("/user/{id}")
    public Feed findByUserId(@PathVariable Long id) {
        return feedService.findByUserId(id);
    }

    @PostMapping
    public Feed save(@RequestBody Feed feed) {
        return feedService.save(feed);
    }

    @PutMapping("/{id}")
    public Feed update(@PathVariable Long id, @RequestBody Feed feed) {
        feed.setUsuarioId(id);
        return feedService.update(feed);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        feedService.deleteById(id);
    }
}
