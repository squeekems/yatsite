package com.squeekems.yat.entities;

import jakarta.persistence.*;

import java.util.Objects;

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
  private Long resultId;

  public Option() {}

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

  public Long getResultId() {
    return resultId;
  }

  public void setResultId(Long resultId) {
    this.resultId = resultId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Option option = (Option) o;

    if (!Objects.equals(optionId, option.optionId))
      return false;
    if (!Objects.equals(label, option.label)) return false;
    return Objects.equals(resultId, option.resultId);
  }

  @Override
  public int hashCode() {
    int result = optionId != null ? optionId.hashCode() : 0;
    result = 31 * result + (label != null ? label.hashCode() : 0);
    result = 31 * result + (this.resultId != null ? this.resultId.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Option{" +
        "optionId=" + optionId +
        ", label='" + label + '\'' +
        ", resultId=" + resultId +
        '}';
  }
}

