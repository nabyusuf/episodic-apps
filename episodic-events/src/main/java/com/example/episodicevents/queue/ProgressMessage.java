package com.example.episodicevents.queue;

import lombok.*;

import java.util.Date;

/**
 * Created by trainer3 on 5/24/17.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProgressMessage {

        private Long userId;
        private Long episodeId;
        private Date createdAt;
        private int offset;

}
