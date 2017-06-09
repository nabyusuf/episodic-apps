package com.example.episodic.viewings;

import com.example.episodic.episodes.Episodes;
import com.example.episodic.shows.Shows;
import com.example.episodic.users.Users;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by trainer3 on 5/19/17.
 */
@Entity
@Setter
@Getter
@Table(name = "viewings")

public class Viewing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "show_id")
    private Shows show;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "episode_id")
    private Episodes episode;

    @Column(name = "updated_at", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private int timecode;

}
