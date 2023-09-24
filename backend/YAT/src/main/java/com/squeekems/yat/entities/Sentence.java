package com.squeekems.yat.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sentences")
public class Sentence {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String roomSentence;

  @Column
  private String flag;

  public Sentence() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoomSentence() {
    return roomSentence;
  }

  public void setRoomSentence(String roomSentence) {
    this.roomSentence = roomSentence;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }
}