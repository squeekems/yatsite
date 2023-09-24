package com.squeekems.yat.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "players")
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long playerId;
  @Column
  private String username;
  @Column
  private int room;
  @Column
  private int position;
  @Column
  private int skipCounter;
  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "gameId", referencedColumnName = "gameId")
  private Game game;

  public Player() {
    position = 1;
  }

  public Player(int room) {
    this();
    this.room = room;
  }

  public Player(int room, String username) {
    this(room);
    this.username = username;
  }

  public Long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(Long playerId) {
    this.playerId = playerId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getRoom() {
    return room;
  }

  public void setRoom(int room) {
    this.room = room;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    if (position > 20) {
      position = 20;
    }
    if (position < 1) {
      position = 1;
    }
    this.position = position;
  }

  public int getSkipCounter() {
    return skipCounter;
  }

  public void setSkipCounter(int skipCounter) {
    this.skipCounter = skipCounter;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  @Override
  public String toString() {
    return "Player{" +
        "playerId=" + playerId +
        ", username='" + username + '\'' +
        ", room=" + room +
        ", position=" + position +
        ", skipCounter=" + skipCounter +
        ", game=" + game +
        '}';
  }
}
