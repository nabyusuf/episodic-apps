package com.example.episodicevents.events;

import com.example.episodicevents.repo.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by trainer3 on 5/22/17.
 */
@RestController
@RequestMapping("/")
public class EventsController {

    @Autowired
    EventsRepository repository;

    @PostMapping
    public Object createEvent(@RequestBody EpisodicEvent event) {
        return this.repository.save(event);
    }

    @GetMapping("recent")
    public List<EpisodicEvent> getRecentEvent(){
        return this.repository.findAll();
    }

}
