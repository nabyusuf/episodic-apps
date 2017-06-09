create table viewings(
  id bigint not null auto_increment primary key,
  user_id bigint not null,
  show_id bigint not null,
  episode_id bigint not null,
  updated_at DATETIME,
  timecode int,
  FOREIGN KEY (user_id)
  REFERENCES users(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (show_id)
  REFERENCES shows(id)
  ON UPDATE CASCADE
  ON DELETE RESTRICT,
  FOREIGN KEY (episode_id)
  REFERENCES episodes(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);