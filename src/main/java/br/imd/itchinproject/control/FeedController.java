package br.imd.itchinproject.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.imd.itchinproject.services.FeedService;

@RestController
@RequestMapping("/feeds")
public class FeedController {
    @Autowired
    private FeedService feedService;
}
