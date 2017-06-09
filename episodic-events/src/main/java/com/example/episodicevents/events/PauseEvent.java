package com.example.episodicevents.events;

import com.example.episodicevents.data.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by trainer3 on 5/22/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class PauseEvent extends EpisodicEvent{

    private Data data;

    public String getType() {
        return "pause";
    }

    public PauseEvent(Data data) {
        this.data = data;
    }

    public PauseEvent(Long userId, Long showId, Long episodeId, Date createdAt, Data data) {
        super(userId, showId, episodeId, createdAt);
        this.data = data;
    }
}
