package br.imd.itchinproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.imd.itchinproject.respository.FeedRepository;

@Service
public class FeedService {
    @Autowired
    private FeedRepository feedRepository;
}
