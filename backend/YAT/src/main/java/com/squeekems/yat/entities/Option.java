package com.squeekems.yat.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class Option {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long optionId;
  /**
   * Example: "I try to escape."
   */
  @Column
  private String label;
  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "eventId", referencedColumnName = "eventId")
  private Event result;

  public Option() {}

  public Option(String label) {
    this();
    this.label = label;
  }

  public Option(String label, Event result) {
    this(label);
    this.result = result;
  }

  public Option(String label, String result) {
    this(label);
    this.result = new Event(result);
  }

  public Long getOptionId() {
    return optionId;
  }

  public void setOptionId(Long optionId) {
    this.optionId = optionId;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Event getResult() {
    return result;
  }

  public void setResult(Event result) {
    this.result = result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Option option = (Option) o;

    if (!label.equals(option.label)) return false;
    return result.equals(option.result);
  }

  @Override
  public int hashCode() {
    int result1 = label.hashCode();
    result1 = 31 * result1 + result.getEventId().hashCode();
    return result1;
  }

  @Override
  public String toString() {
    return "Option{" +
        "optionId=" + optionId +
        ", label='" + label + '\'' +
        ", resultId=" + result.getEventId() +
        '}';
  }
}
