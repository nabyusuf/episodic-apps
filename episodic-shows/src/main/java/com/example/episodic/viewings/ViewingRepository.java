package com.example.episodic.viewings;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by trainer3 on 5/19/17.
 */
public interface ViewingRepository extends CrudRepository<Viewing, Long>{

    String sql = "SELECT * FROM viewings WHERE user_id = :user_id";
    @Query(value = sql, nativeQuery = true)
    List<Viewing> findAllByUsers(@Param("user_id")Long userId);

    String sql1 = "SELECT * FROM viewings WHERE episode_id = :episode_id and user_id = :user_id";
    @Query(value = sql1, nativeQuery = true)
    Viewing findAllByEpisodeAndUser(@Param("episode_id")Long episodeId, @Param("user_id")Long userId);

}
