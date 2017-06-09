package com.example.episodic.rabbitmq;

import lombok.*;

import java.util.Date;

/**
 * Created by trainer3 on 5/23/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShowMessage {

    private Long userId;
    private Long episodeId;
    private Date createdAt;
    private int offset;
}
