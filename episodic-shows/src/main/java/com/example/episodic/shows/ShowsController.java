package com.example.episodic.shows;

import com.example.episodic.episodes.EpisodeService;
import com.example.episodic.episodes.EpisodeShow;
import com.example.episodic.episodes.Episodes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by trainer3 on 5/17/17.
 */

@RestController
@RequestMapping("/shows")
public class ShowsController {

    private final ShowsRepository repository;
    private final EpisodeService service;

    public ShowsController(ShowsRepository repository, EpisodeService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping("")
    public Shows postToShows(@RequestBody Shows shows){
            return this.repository.save(shows);
        }

    @GetMapping("")
    public List<Shows> getUsers(){
        return (List<Shows>) this.repository.findAll();
    }

    @PostMapping("/{id}/episodes")
    public EpisodeShow postEpisode(@PathVariable Long id,@RequestBody Episodes episodes){
            return this.service.insertEpisodes(id, episodes);
    }

    @GetMapping("/{id}/episodes")
    public List<EpisodeShow> getEpisodes(@PathVariable Long id){
        return this.service.findAllEpisodes(id);
    }
}