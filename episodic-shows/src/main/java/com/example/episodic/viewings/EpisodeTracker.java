package com.example.episodic.viewings;

import java.util.Date;

/**
 * Created by trainer3 on 5/19/17.
 */
public class EpisodeTracker {

    private Long episodeId;

    private Date updatedAt;

    private int timecode;

    public Long getEpisodeId() { return this.episodeId; }

    public void setEpisodeId(Long episodeId) { this.episodeId = episodeId; }

    public Date getUpdatedAt() { return this.updatedAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public int getTimecode() { return this.timecode; }

    public void setTimecode(int timecode) { this.timecode = timecode; }

}