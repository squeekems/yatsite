package com.squeekems.yat.util.oneshots;

import java.util.ArrayList;
import java.util.List;

public class EventCard {

  public String strIndex;
  public String strPrompt;
  public List<String> strChoice;

  public EventCard(String newIndex, String newPrompt, List<String> newChoices) {
    strIndex = newIndex;
    strPrompt = newPrompt;
    strChoice = newChoices;
  }

  public EventCard(String newIndex, String newPrompt, String newChoice1) {
    strChoice = new ArrayList<>();
    strIndex = newIndex;
    strPrompt = newPrompt;
    strChoice.add(newChoice1);
  }

  public EventCard(String newIndex, String newPrompt, String newChoice1, String newChoice2) {
    this(newIndex, newPrompt, newChoice1);
    strChoice.add(newChoice2);
  }

  public EventCard(String newIndex, String newPrompt, String newChoice1, String newChoice2, String newChoice3) {
    this(newIndex, newPrompt, newChoice1, newChoice2);
    strChoice.add(newChoice3);
  }

  public EventCard(String newIndex, String newPrompt, String newChoice1, String newChoice2, String newChoice3, String newChoice4) {
    this(newIndex, newPrompt, newChoice1, newChoice2, newChoice3);
    strChoice.add(newChoice4);
  }

  @Override
  public String toString() {
    return "EventCard{" +
        "strIndex='" + strIndex + '\'' +
        ", strPrompt='" + strPrompt + '\'' +
        ", strChoice=" + strChoice +
        '}';
  }
}
