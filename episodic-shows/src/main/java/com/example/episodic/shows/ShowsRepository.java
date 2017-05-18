package com.example.episodic.shows;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by trainer3 on 5/17/17.
 */
public interface ShowsRepository extends CrudRepository<Shows, Long> {

    Shows findById(Long id);
}
