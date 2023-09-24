CREATE TABLE events (
  event_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ds_prompt CHARACTER LARGE OBJECT DEFAULT NULL,
  is_card BOOLEAN DEFAULT NULL,
  prompt CHARACTER LARGE OBJECT DEFAULT NULL
);

CREATE TABLE options (
  option_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  label CHARACTER LARGE OBJECT DEFAULT NULL,
  event_id INT DEFAULT NULL,

  FOREIGN KEY (event_id) REFERENCES events (event_id)
);

CREATE TABLE event_options (
  event_id INT NOT NULL,
  option_id INT NOT NULL,

  FOREIGN KEY (event_id) REFERENCES events (event_id),
  FOREIGN KEY (option_id) REFERENCES options (option_id)
);
--ALTER TABLE event_options DROP event_id;
ALTER TABLE event_options ADD PRIMARY KEY (event_id, option_id);

CREATE TABLE players (
  player_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  position INT DEFAULT NULL,
  room INT DEFAULT NULL,
  skip_counter INT DEFAULT NULL,
  username CHARACTER LARGE OBJECT DEFAULT NULL
);