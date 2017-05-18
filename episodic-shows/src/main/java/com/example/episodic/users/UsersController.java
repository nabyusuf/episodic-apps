package com.example.episodic.users;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by trainer3 on 5/17/17.
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersRepository repository;

    public UsersController(UsersRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public Users postToUsers(@RequestBody Users user){
        return this.repository.save(user);
    }

    @GetMapping("")
    public List<Users> getUsers(){
        return (List<Users>) this.repository.findAll();
    }

}
