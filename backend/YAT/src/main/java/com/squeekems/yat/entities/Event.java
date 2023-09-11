package com.squeekems.yat.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long eventId;
  @Lob
  @Column(length = 65535)
  private String prompt;
  @Lob
  @Column(length = 65535)
  private String dsPrompt;
  @Column
  private boolean isCard;

  @ManyToMany(targetEntity = Option.class, fetch = FetchType.EAGER)
  @JoinTable( name = "event_options", joinColumns = { @JoinColumn(name = "event_id") },
    inverseJoinColumns = { @JoinColumn(name = "optionId") })
  private Set<Option> options;

  public Event() {
  }

  public Event(String prompt) {
    this.prompt = prompt;
  }

  public Event(String prompt, String dsPrompt, boolean isCard, Set<Option> options) {
    this.prompt = prompt;
    this.dsPrompt = dsPrompt;
    this.isCard = isCard;
    this.options = options;
  }

  public Event(String prompt, boolean isCard, Set<Option> options) {
    this.prompt = prompt;
    this.isCard = isCard;
    this.options = options;
  }

  public Long getEventId() {
    return eventId;
  }

  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

  public String getPrompt() {
    return prompt;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public String getDsPrompt() {
    return dsPrompt;
  }

  public void setDsPrompt(String dsPrompt) {
    this.dsPrompt = dsPrompt;
  }

  public boolean isCard() {
    return isCard;
  }

  public void setCard(boolean card) {
    isCard = card;
  }

  public Set<Option> getOptions() {
    return options;
  }

  public void setOptions(Set<Option> options) {
    this.options = options;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Event event = (Event) o;

    if (isCard != event.isCard) return false;
    if (!prompt.equals(event.prompt)) return false;
    if (!Objects.equals(dsPrompt, event.dsPrompt)) return false;
    return Objects.equals(options, event.options);
  }

  @Override
  public int hashCode() {
    int result = prompt.hashCode();
    result = 31 * result + (dsPrompt != null ? dsPrompt.hashCode() : 0);
    result = 31 * result + (isCard ? 1 : 0);
    result = 31 * result + (options != null ? options.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Event{" +
        "eventId=" + eventId +
        ", prompt='" + prompt + '\'' +
        ", dsPrompt='" + dsPrompt + '\'' +
        ", isCard=" + isCard +
        ", options=" + options +
        '}';
  }
}
