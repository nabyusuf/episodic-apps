package com.example.episodic.episodes;

import com.example.episodic.shows.Shows;

import javax.persistence.*;

/**
 * Created by trainer3 on 5/18/17.
 */
@Entity
public class Episodes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Shows shows;

    private int seasonNumber;

    private int episodeNumber;

//    @ManyToOne
//    @JoinColumn(name="show_id")
    public long getShowId() {
        return this.shows.getId();
    }

    public Episodes() {
    }

    public Episodes(Shows shows, int seasonNumber, int episodeNumber) {
        this.shows = shows;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shows getShows() {
        return shows;
    }

    public void setShows(Shows shows) {
        this.shows = shows;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
}
