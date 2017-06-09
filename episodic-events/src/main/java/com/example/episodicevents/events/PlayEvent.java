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
public class PlayEvent extends EpisodicEvent {

    private Data data;

    public String getType() {
        return "play";
    }

    public PlayEvent(Data data) {
        this.data = data;
    }

    public PlayEvent(Long userId, Long showId, Long episodeId, Date createdAt, Data data) {
        super(userId, showId, episodeId, createdAt);
        this.data = data;
    }
}
