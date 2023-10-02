CREATE TABLE events (
  event_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  ds_prompt CHARACTER LARGE OBJECT DEFAULT NULL,
  is_card BOOLEAN DEFAULT NULL,
  prompt CHARACTER LARGE OBJECT DEFAULT NULL
);

CREATE TABLE options (
  option_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  label CHARACTER LARGE OBJECT DEFAULT NULL,
  result_id INT DEFAULT NULL
);

CREATE TABLE event_options (
  event_id INT NOT NULL,
  option_id INT NOT NULL,

  FOREIGN KEY (event_id) REFERENCES events (event_id),
  FOREIGN KEY (option_id) REFERENCES options (option_id)
);
ALTER TABLE event_options ADD PRIMARY KEY (event_id, option_id);

CREATE TABLE IF NOT EXISTS players (
  player_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  position INT DEFAULT NULL,
  room INT DEFAULT NULL,
  skip_counter INT DEFAULT NULL,
  username CHARACTER LARGE OBJECT DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS sentences (
    id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    sentence CHARACTER LARGE OBJECT DEFAULT NULL,
    flag CHARACTER LARGE OBJECT DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS items (
    item_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY UNIQUE,
    name CHARACTER LARGE OBJECT DEFAULT NULL,
    damage INT DEFAULT NULL,
    type CHARACTER LARGE OBJECT DEFAULT NULL,
    effects CHARACTER LARGE OBJECT DEFAULT NULL,
    is_greater BOOLEAN DEFAULT NULL,
    count INT DEFAULT NULL
);