package com.example.episodicevents.events;

import com.example.episodicevents.data.ScrubOffsetData;
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
public class ScrubEvent extends EpisodicEvent {

    private ScrubOffsetData data;

    public String getType() {
        return "scrub";
    }

    public ScrubEvent(ScrubOffsetData data) {
        this.data = data;
    }

    public ScrubEvent(Long userId, Long showId, Long episodeId, Date createdAt, ScrubOffsetData data) {
        super(userId, showId, episodeId, createdAt);
        this.data = data;
    }
}
