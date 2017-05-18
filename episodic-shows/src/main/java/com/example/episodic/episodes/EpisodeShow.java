package com.example.episodic.episodes;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Transient;

/**
 * Created by trainer3 on 5/18/17.
 */
public class EpisodeShow {

    private Long id;

    private int seasonNumber;

    private int episodeNumber;

    @Transient
    @JsonInclude
    private String title;

    public EpisodeShow() {
    }

    public Long getId() { return this.id; }

    public void setId(Long id) { this.id = id; }

    public int getSeasonNumber() { return this.seasonNumber; }

    public void setSeasonNumber(int seasonNumber) { this.seasonNumber = seasonNumber; }

    public int getEpisodeNumber() { return this.episodeNumber; }

    public void setEpisodeNumber(int episodeNumber) { this.episodeNumber = episodeNumber; }

    public String getTitle() {
        title = "S"+this.seasonNumber+" E"+this.episodeNumber;
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title; }
}
