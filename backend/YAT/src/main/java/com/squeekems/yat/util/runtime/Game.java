package com.squeekems.yat.util.runtime;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Item;
import com.squeekems.yat.entities.Option;
import com.squeekems.yat.util.comparators.OptionComparator;
import com.squeekems.yat.services.EventService;
import com.squeekems.yat.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import java.util.*;

@Component
public class Game implements CommandLineRunner {

  @Autowired
  EventService eventService;

  Scanner console;

  Long roomId;
  int dragonHitPoints;
  int playerPointer;
  Event currentEvent;
  String theStory;
  List<String> buildings;
  List<Player> players;
  List<Event> eventDeck;
  List<Event> eventDiscard;
  List<Item> greaterLoot;
  List<Item> lesserLoot;
  List<Item> lootDiscard;

  @Override
  public void run(String... args) {
    System.out.println("Do you want to start a game in console? (y/n)");
    String answer = console.next();
    if (answer.equalsIgnoreCase("y")) {
      soutEvent(eventService.getEventById(Constants.event1Id));
      eventDeck = eventService.findAll().stream().filter(Event::isCard).toList();
      eventDeck = new ArrayList<>(eventDeck);
      players.sort(new PlayerComparator());
      Set<Integer> setRooms = new HashSet<>();
      for (Player player : players) {
        setRooms.add(player.getRoom());
      }
      List<Integer> rooms = new ArrayList<>(setRooms);
      rooms.add(99);
      Event introduction = new Event(Constants.getIntroduction(rooms));
      soutEvent(introduction);
      discardCurrentEventCard();
      while (dragonHitPoints > 0 && buildings.size() > 0) {
        announceNewTurn();
        Player currentPlayer = players.get(playerPointer);
        if (currentPlayer.getSkipCounter() <= 0) {
          drawEventCard();
          soutEvent(currentEvent);
        } else {
          currentPlayer.setSkipCounter(currentPlayer.getSkipCounter() - 1);
        }
      }
    }
  }

  private Game() {
    buildings = new ArrayList<>(Arrays.asList(Constants.buildings));
    dragonHitPoints = 0;
    players = new ArrayList<>();
    eventDeck = new ArrayList<>();
    eventDiscard = new ArrayList<>();
    greaterLoot = new ArrayList<>();
    lesserLoot = new ArrayList<>();
    lootDiscard = new ArrayList<>();
    playerPointer = -1;
    console = new Scanner(System.in);
  }

  public void addPlayer(Player player) {
    players.add(player);
    player.setPlayerId((long) players.indexOf(player));
    player.setGame(this);
    dragonHitPoints += 20;
  }

  private void drawEventCard() {
    if (currentEvent == null) {
      currentEvent = eventDeck.get(new Random().nextInt(eventDeck.size()));
      eventDeck.remove(currentEvent);
      System.out.println("There are " + eventDeck.size() + " cards left in the event deck.");
      if (eventDeck.size() == 0) {
        Event eventDeckShuffled = new Event(
            "Discarded events shuffled back into the event deck."
        );
        eventDeck.addAll(eventDiscard);
        eventDiscard.clear();
        soutEvent(eventDeckShuffled);
      }
    }
  }

  private void discardCurrentEventCard() {
    if (currentEvent != null) {
      eventDiscard.add(currentEvent);
      currentEvent = null;
    }
  }

  private void announceNewTurn() {
    playerPointer += 1;
    if (playerPointer == players.size()) {
      playerPointer = 0;
      announceNewRound();
    }
    Player currentPlayer = players.get(playerPointer);
    String player = currentPlayer.getUsername();
    Event newTurn;
    if (currentPlayer.getSkipCounter() == 0) {
      newTurn = new Event(
          "It is " + player + "'s turn! " +
              "If you are " + player +
              ", move your Progress Tracker up by 1 and hit " + Constants.optionContinue + '.'
      );
    } else {
      newTurn = new Event("You have to skip your turn, " + player + '.');
    }
    soutEvent(newTurn);
  }

  private void announceNewRound() {
    String building = buildings.get(new Random().nextInt(buildings.size()));
    buildings.remove(building);
    Event buildingDestroyed;
    if (buildings.size() > 1) {
      buildingDestroyed = new Event("The dragon destroyed the " + building +
              ". There are " + buildings.size() + " buildings remaining."
      );
    } else if (buildings.size() == 1) {
      buildingDestroyed = new Event("The dragon destroyed the " + building +
          ". The " + buildings.get(0) + " is the only building remaining."
      );
    } else {
      buildingDestroyed = new Event("The dragon destroyed the " + building +
          ". With no buildings remaining, the dragon focuses its attention on you!"
      );
    }
    soutEvent(buildingDestroyed);
  }

  public void soutEvent(Event event) {
    Set<Option> setOptions = event.getOptions();
    List<Option> options = null;
    String prompt = event.getPrompt();
    String dsPrompt = event.getDsPrompt();
    Player player = players.size() > 0 && playerPointer > 0 && playerPointer < players.size() ?
        players.get(playerPointer) : null;
    int choice = -1;

    if (setOptions != null && setOptions.size() > 0) {
      options = new ArrayList<>(setOptions);

      options.sort(new OptionComparator());
    }

    // Display Prompt
    System.out.println(prompt);

    // Display DragonScript Prompt
    if (dsPrompt != null) {
      boolean isParsable = false;
      try {
        Integer.parseInt(dsPrompt);
        isParsable = true;
      } catch (NumberFormatException ignored) {}
      if (!isParsable) {
        System.out.println(dsPrompt);
      }
    }

    if (options != null) {

      // Display Options
      for (int i = 0; i < options.size(); i++) {
        System.out.println(
            String.format(Constants.fOption, i + 1, options.get(i).getLabel())
        );
      }

      choice = console.nextInt();

      if (choice > 0 && choice <= options.size()) {
        if (prompt.equals(Constants.youAreInATavern)) {
          Event whatIsYourName = new Event(Constants.whatIsYourName, String.valueOf(choice - 1));
          soutEvent(whatIsYourName);
        }

        // OG Event Finish
        soutEvent(options.get(choice - 1).getResult());
        discardCurrentEventCard();
      } else {
        System.out.println("Choice '" + choice + "' is out of bounds: '1'-'" + options.size() + "'");
      }
    } else {
      if (prompt.equals(Constants.whatIsYourName)) {
        Player newPlayer = new Player(Integer.parseInt(dsPrompt));
        console.nextLine();
        newPlayer.setUsername(console.nextLine());
        addPlayer(newPlayer);
        Event welcomePlayer = new Event(
            String.format(Constants.fWelcomeToTavern, newPlayer.getUsername())
        );
        soutEvent(welcomePlayer);
      } else {
        System.out.println(String.format(Constants.fOption, 1, Constants.optionContinue));
        choice = console.nextInt();
      }
    }
    if (prompt.contains(Constants.skipQueue)) {
      player.setSkipCounter(player.getSkipCounter() + 1);
    }
//    if (prompt.toLowerCase().contains(Constants.moveQueue)) {
//      if (prompt.toLowerCase().contains(Constants.moveUpQueue)) {
//        if (player.getPosition() < 20) {
//          Event movePlayer;
//          int change = Integer.parseInt(prompt.substring(
//              prompt.indexOf(Constants.moveUpQueue + Constants.moveUpQueue.length()), 1
//          ));
//          player.setPosition(player.getPosition() + change);
//          if (change == 1) {
//            movePlayer = new Event(String.format(
//                Constants.fMoveSingular, player.getUsername(), "up", change,
//                player.getPosition() - change, player.getPosition()
//            ));
//          } else {
//            movePlayer = new Event(String.format(
//                Constants.fMovePlural, player.getUsername(), "up", change,
//                player.getPosition() - change, player.getPosition()
//            ));
//          }
//          soutEvent(movePlayer);
//        }
//      } else if (prompt.toLowerCase().contains(Constants.moveDownQueue)) {
//        if (player.getPosition() > 1) {
//          Event movePlayer;
//          int change = Integer.parseInt(prompt.substring(
//              prompt.indexOf(Constants.moveDownQueue + Constants.moveDownQueue.length()), 1
//          ));
//          player.setPosition(player.getPosition() - change);
//          if (change == 1) {
//            movePlayer = new Event(String.format(
//                Constants.fMoveSingular, player.getUsername(), "down", change,
//                player.getPosition() - change, player.getPosition()
//            ));
//          } else {
//            movePlayer = new Event(String.format(
//                Constants.fMovePlural, player.getUsername(), "down", change,
//                player.getPosition() - change, player.getPosition()
//            ));
//          }
//          soutEvent(movePlayer);
//        }
//      } else {
//        Event badMoveQueue = new Event(
//            "Someone wrote their move queue ambiguously... I can not tell if we need to " +
//                "move your Progress Tracker up or down."
//        );
//        soutEvent(badMoveQueue);
//      }
//    }
  }

}
