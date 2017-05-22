package com.example.episodicevents.repo;

import com.example.episodicevents.events.EpisodicEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by trainer3 on 5/22/17.
 */
public interface EventsRepository extends MongoRepository<EpisodicEvent, String> {

    public abstract List<EpisodicEvent> findAll();
}
