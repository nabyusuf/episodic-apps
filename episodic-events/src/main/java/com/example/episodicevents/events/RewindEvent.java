package com.example.episodicevents.events;

import com.example.episodicevents.data.SpeedOffsetData;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class RewindEvent extends EpisodicEvent {

    private SpeedOffsetData data;

    public String getType() {
        return "rewind";
    }

    public RewindEvent(SpeedOffsetData data) {
        this.data = data;
    }

    public RewindEvent(int userId, int showId, int episodeId, Date createdAt, SpeedOffsetData data) {
        super(userId, showId, episodeId, createdAt);
        this.data = data;
    }
}
