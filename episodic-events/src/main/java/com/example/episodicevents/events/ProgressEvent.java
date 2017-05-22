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
public class ProgressEvent extends EpisodicEvent{

    private Data data;

    public String getType() {
        return "progress";
    }

    public ProgressEvent(Data data) {
        this.data = data;
    }

    public ProgressEvent(int userId, int showId, int episodeId, Date createdAt, Data data) {
        super(userId, showId, episodeId, createdAt);
        this.data = data;
    }
}
