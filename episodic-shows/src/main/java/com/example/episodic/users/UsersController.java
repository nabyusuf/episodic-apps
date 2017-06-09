package com.example.episodic.users;

import com.example.episodic.viewings.EpisodeTracker;
import com.example.episodic.viewings.RecentlyWatched;
import com.example.episodic.viewings.ViewingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by trainer3 on 5/17/17.
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersRepository repository;
    private final ViewingService service;

    public UsersController(UsersRepository repository, ViewingService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping("")
    public Users postToUsers(@RequestBody Users user){
        return this.repository.save(user);
    }

    @GetMapping("")
    public List<Users> getUsers(){
        return (List<Users>) this.repository.findAll();
    }

    @PatchMapping("/{id}/viewings")
    public ResponseEntity<HttpStatus> patchEpisodes(@PathVariable Long id, @RequestBody EpisodeTracker episodeTracker){
        return new ResponseEntity((this.service.patchViewing(id, episodeTracker)));
    }

    @GetMapping("/{id}/recently-watched")
    public List<RecentlyWatched> getRecentlyWatched(@PathVariable Long id){
        return this.service.getRecentlyWatched(id);
    }
}
