package com.squeekems.yat.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sentences")
public class Sentence {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String sentence;

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

  public String getSentence() {
    return sentence;
  }

  public void setSentence(String sentence) {
    this.sentence = sentence;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  @Override
  public String toString() {
    return "Sentence{" +
        "id=" + id +
        ", sentence='" + sentence + '\'' +
        ", flag='" + flag + '\'' +
        '}';
  }
}