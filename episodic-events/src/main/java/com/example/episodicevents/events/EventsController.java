package com.example.episodicevents.events;

import com.example.episodicevents.queue.ProgressMessage;
import com.example.episodicevents.repo.EventsRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * Created by trainer3 on 5/22/17.
 */
@RestController
@RequestMapping("/")
public class EventsController {


    private final EventsRepository repository;
private final RabbitTemplate rabbitTemplate;

    public EventsController(EventsRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public Object createEvent(@RequestBody EpisodicEvent event) {

        if(event.getType().equalsIgnoreCase("progress")){
            ProgressEvent event1 = (ProgressEvent)event;

                rabbitTemplate.convertAndSend("my-exchange", "my-routing-key", new ProgressMessage(
                        event1.getUserId(),
                        event1.getEpisodeId(),
                        event1.getCreatedAt(),
                        event1.getData().getOffset()
                ));
        }

        return this.repository.save(event);
    }

    @GetMapping("recent")
    public Page<EpisodicEvent> getRecentEvent(){
        return this.repository.findAll(new PageRequest(0, 20));
    }

}
