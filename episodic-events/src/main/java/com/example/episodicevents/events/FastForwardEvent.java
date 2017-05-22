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
public class FastForwardEvent extends EpisodicEvent {

    private SpeedOffsetData data;

    public String getType() {
        return "fastForward";
    }

    public FastForwardEvent(SpeedOffsetData data) {
        this.data = data;
    }

    public FastForwardEvent(int userId, int showId, int episodeId, Date createdAt, SpeedOffsetData data) {
        super(userId, showId, episodeId, createdAt);
        this.data = data;
    }
}
