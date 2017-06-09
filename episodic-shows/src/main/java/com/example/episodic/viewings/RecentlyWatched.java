package com.example.episodic.viewings;

import com.example.episodic.episodes.EpisodeShow;
import com.example.episodic.episodes.Episodes;
import com.example.episodic.shows.Shows;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by trainer3 on 5/19/17.
 */
public class RecentlyWatched {

    private Shows show;

    private Date updatedAt;

    private EpisodeShow episode;

    private int timecode;

    @JsonProperty(value = "show")
    public Shows getShow() { return this.show; }

    public void setShow(Shows show) { this.show = show; }

    @JsonProperty(value = "episode")
    public EpisodeShow getEpisode() {
        return episode;
    }

    public void setEpisode(EpisodeShow episode) {
        this.episode = episode;
    }

    public Date getUpdatedAt() { return this.updatedAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public int getTimecode() { return this.timecode; }

    public void setTimecode(int timecode) { this.timecode = timecode; }

}
