package com.squeekems.yat.util.oneshots;

import com.squeekems.yat.entities.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OneShot {

  private List<EventCard> eventDeck;
  private List<String> resultIndex;
  private List<List<String>> result;

  private List<Item> itemList;

  public OneShot() {
    eventDeck = loadEventCards();
    itemList = loadItems();
    resultIndex = new ArrayList<>();
    result = new ArrayList<>();

    for (int i = 0; i < 4; i++) {
      result.add(new ArrayList<>());
    }

    List<String> lines = openCSV("C:/Users/Owner/Desktop/Results.csv");

    for (int i = 0; i < lines.size(); i++) {

//      String[] splitLine = lines.get(i).replace("$","\n").split(",");

      String[] splitLine = lines.get(i).replace("$","").split(",");

      resultIndex.add(splitLine[0]);

      for (int y = 0; y < 4; y++) {

        if (y + 1 < splitLine.length) {

          result.get(y).add(splitLine[y + 1].replace("\"\"", "%"));

          result.get(y).set(i, result.get(y).get(i).replace("\"", ""));

          result.get(y).set(i, result.get(y).get(i).replace("%", "\""));

          result.get(y).set(i, result.get(y).get(i).replace("|", ","));

        } else {

          result.get(y).add("");

        }

      }

    }

  }

  private static List<String> openCSV(String fileName) {

    BufferedReader fileReader = null;
    try {
      fileReader = new BufferedReader(new FileReader(fileName));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    List<String> lines = new ArrayList<>();
    String line = null;

    while (true) {
      try {
        if ((line = fileReader.readLine()) == null) break;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      lines.add(line);

    }

    return lines;

  }

  private List<EventCard> loadEventCards() {

    List<EventCard> lstEventDeck = new ArrayList<>();
    List<String> lines = openCSV("C:/Users/Owner/Desktop/Events.csv");

    for (String splitLine: lines) {

//      String[] newSplitLine = splitLine.replace("$", "\n").split(",");

      String[] newSplitLine = splitLine.replace("$", "").split(",");

      switch (newSplitLine.length) {

        case 3 -> lstEventDeck.add(new EventCard(
            replaceAllSpecialCharacters(newSplitLine[0]),
            replaceAllSpecialCharacters(newSplitLine[1]),
            replaceAllSpecialCharacters(newSplitLine[2])
        ));

        case 4 -> lstEventDeck.add(new EventCard(
            replaceAllSpecialCharacters(newSplitLine[0]),
            replaceAllSpecialCharacters(newSplitLine[1]),
            replaceAllSpecialCharacters(newSplitLine[2]),
            replaceAllSpecialCharacters(newSplitLine[3])
        ));

        case 5 -> lstEventDeck.add(new EventCard(
            replaceAllSpecialCharacters(newSplitLine[0]),
            replaceAllSpecialCharacters(newSplitLine[1]),
            replaceAllSpecialCharacters(newSplitLine[2]),
            replaceAllSpecialCharacters(newSplitLine[3]),
            replaceAllSpecialCharacters(newSplitLine[4])
        ));

        case 6 -> lstEventDeck.add(new EventCard(
            replaceAllSpecialCharacters(newSplitLine[0]), // newIndex
            replaceAllSpecialCharacters(newSplitLine[1]), // newPrompt
            replaceAllSpecialCharacters(newSplitLine[2]), // newChoice1
            replaceAllSpecialCharacters(newSplitLine[3]), // newChoice2
            replaceAllSpecialCharacters(newSplitLine[4]), // newChoice3
            replaceAllSpecialCharacters(newSplitLine[5])  // newChoice4
        ));

      }

    }

//    for (EventCard eventCard: lstEventDeck) {
//
//      eventCard.strPrompt = eventCard.strPrompt.replace("\"", "");
//      eventCard.strPrompt = eventCard.strPrompt.replace("%", "\"");
//      eventCard.strPrompt = eventCard.strPrompt.replace("|", ",");
//
//      for (String option: eventCard.strChoice) {
//
//        option = option.replace("\"", "");
//        option = option.replace("%", "\"");
//        option = option.replace("|", ",");
//
//      }
//
//    }

    return lstEventDeck;

  }

  private String replaceAllSpecialCharacters(String s) {

    return s.replace("|", ",")
        .replace("%", "\"")
        .replace("\"", "")
        .replace("\"\"", "%");

  }

  private List<Item> loadItems() {

    List<Item> items = new ArrayList<>();
    List<String> greater = openCSV("C:/Users/Owner/Desktop/Greater_Loot.csv");
    List<String> lesser = openCSV("C:/Users/Owner/Desktop/Lesser_Loot.csv");

    for (String splitLine: greater) {

      String[] newSplitLine = splitLine.replace("$", "").split(",");

      items.add(new Item(
          newSplitLine[0].replace("\"\"", "%"), // name
          true,                                                 // isGreater
          newSplitLine[1].replace("\"\"", "%"), // type
          newSplitLine[2].replace("\"\"", "%"), // damage
          newSplitLine[3].replace("\"\"", "%"), // effects
          newSplitLine[4].replace("\"\"", "%")  // count
      ));

    }

    for (String splitLine: lesser) {

      String[] newSplitLine = splitLine.replace("$", "").split(",");

      items.add(new Item(
          newSplitLine[0].replace("\"\"", "%"), // name
          false,                                                // isGreater
          newSplitLine[1].replace("\"\"", "%"), // type
          newSplitLine[2].replace("\"\"", "%"), // damage
          newSplitLine[3].replace("\"\"", "%"), // effects
          newSplitLine[4].replace("\"\"", "%")  // count
      ));

    }

    for (Item item: items) {

      item.setEffects(item.getEffects().replace("\"", ""));

      item.setEffects(item.getEffects().replace("%", "\""));

      item.setEffects(item.getEffects().replace("|", ","));

    }

    return items;

  }

  public EventCard getCardById(int id) {

    return eventDeck.get(id);

  }

  public List<String> getResults(int id) {

    List<String> resultSet = new ArrayList<>();

    for (int i = 0; i < result.size(); i++) {

      resultSet.add(result.get(i).get(id));

    }

    return resultSet;

  }

  public List<String> getResults(EventCard eventCard) {

    return getResults(resultIndex.indexOf(eventCard.strIndex));

  }

  public void printCardById(int id) {

    EventCard eventCard = eventDeck.get(id);
    int resultIndexOfEventCard = resultIndex.indexOf(eventCard.strIndex);
    List<String> results = new ArrayList<>();
    String s;

    for (int i = 0; i < eventCard.strChoice.size(); i++) {

      results.add(result.get(i).get(resultIndexOfEventCard));

    }

    s = "EventCard{\n" +
        "\tstrIndex='" + eventCard.strIndex + '\'' +
        ", \n\tstrPrompt='" + eventCard.strPrompt.replace("\n", "\n\t\t") + '\'' +
        ", \n\tstrChoice=[";

    for (int i = 0; i < results.size(); i++) {

      if (i > 0) {
        s += ", ";
      }

      s += "\n\t\t'(" + (i + 1) + ") " + eventCard.strChoice.get(i).replace("\n", "\n\t\t\t") + "'\n\t\t'" +
          results.get(i).replace("\n", "\n\t\t\t") + '\'';

    }

    s += "\n\t]" +
        "\n}";

    System.out.println(s);

  }

  public List<EventCard> getEventDeck() {
    return eventDeck;
  }

  public void setEventDeck(List<EventCard> eventDeck) {
    this.eventDeck = eventDeck;
  }

  public List<String> getResultIndex() {
    return resultIndex;
  }

  public void setResultIndex(List<String> resultIndex) {
    this.resultIndex = resultIndex;
  }

  public List<List<String>> getResult() {
    return result;
  }

  public void setResult(List<List<String>> result) {
    this.result = result;
  }

  public List<Item> getItemList() {
    return itemList;
  }

  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
  }
}
