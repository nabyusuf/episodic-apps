package com.example.episodic.episodes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by trainer3 on 5/18/17.
 */
public interface EpisodesRepository extends CrudRepository<Episodes, Long>{

    String sql = "SELECT * FROM episodes WHERE show_id = :show_id";
    @Query(value = sql, nativeQuery = true)
    List<Episodes> findAllByShowId(@Param("show_id")Long showId);
}
