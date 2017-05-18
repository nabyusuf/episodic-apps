create table episodes(
  id bigint not null auto_increment primary key,
  season_number int,
  episode_number int,
  show_id bigint not null,
  FOREIGN KEY fk_cat(show_id)
  REFERENCES shows(id)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);


