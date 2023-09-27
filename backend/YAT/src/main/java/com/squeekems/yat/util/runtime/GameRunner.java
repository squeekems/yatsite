//package com.squeekems.yat.util.runtime;
//
//import com.squeekems.yat.entities.*;
//import com.squeekems.yat.util.comparators.OptionComparator;
//import com.squeekems.yat.services.EventService;
//import com.squeekems.yat.util.Constants;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Component
//public class GameRunner implements CommandLineRunner {
//
//  @Autowired
//  EventService eventService;
//  Scanner console;
//  Game game;
//
//  @Override
//  public void run(String... args) {
//    System.out.println("Do you want to start a game in console? (y/n)");
//    String answer = console.next();
//    if (answer.equalsIgnoreCase("y")) {
//      soutEvent(eventService.getById(Constants.event1Id));
//      game.setDeck(
//          eventService.findAll().stream().filter((Event::isCard)).collect(Collectors.toSet())
//      );
////      eventDeck = new ArrayList<>(eventDeck);
//      game.sortPlayers();
//      Set<Integer> setRooms = new HashSet<>();
//      for (Player player : game.getPlayers()) {
//        setRooms.add(player.getRoom());
//      }
//      List<Integer> rooms = new ArrayList<>(setRooms);
//      rooms.add(99);
//      Event introduction = new Event(Constants.getIntro(rooms));
//      soutEvent(introduction);
//      discardCurrentEventCard();
//      while (game.getDragonHitPoints() > 0 && game.getBuildings().size() > 0) {
//        announceNewTurn();
//        Player currentPlayer = game.getPlayers().get(game.getPlayerPointer());
//        if (currentPlayer.getSkipCounter() <= 0) {
//          drawEventCard();
//          soutEvent(game.getCurrentEvent());
//        } else {
//          currentPlayer.setSkipCounter(currentPlayer.getSkipCounter() - 1);
//        }
//      }
//    }
//  }
//
//  private GameRunner() {
//    game = new Game(); //
//    console = new Scanner(System.in);
//  }
//
//  public void addPlayer(Player player) {
//    List<Player> players = game.getPlayers();
//    players.add(player);
//    game.setPlayers(players);
//    player.setGame(game);
//    game.setDragonHitPoints(game.getDragonHitPoints() + 20);
//  }
//
//  private void drawEventCard() {
//    if (game.getCurrentEvent() == null) {
//      List<Event> deck = new ArrayList<>(game.getDeck());
//      game.setCurrentEvent(deck.get(new Random().nextInt(deck.size())));
//      deck.remove(game.getCurrentEvent());
//      game.setDeck(new HashSet<>(deck));
//      System.out.println("There are " + deck.size() + " cards left in the event deck.");
//      if (deck.size() == 0) {
//        Event eventDeckShuffled = new Event(
//            "Discarded events shuffled back into the event deck."
//        );
//        game.setDeck(game.getDiscard());
//        game.setDiscard(new HashSet<>());
//        soutEvent(eventDeckShuffled);
//      }
//    }
//  }
//
//  private void discardCurrentEventCard() {
//    if (game.getCurrentEvent() != null) {
//      List<Event> discard = new ArrayList<>(game.getDiscard());
//      discard.add(game.getCurrentEvent());
//      game.setDiscard(new HashSet<>(discard));
//      game.setCurrentEvent(null);
//    }
//  }
//
//  private void announceNewTurn() {
//    game.setPlayerPointer(game.getPlayerPointer() + 1);
//    if (game.getPlayerPointer() == game.getPlayers().size()) {
//      game.setPlayerPointer(0);
//      announceNewRound();
//    }
//    Player currentPlayer = game.getPlayers().get(game.getPlayerPointer());
//    String player = currentPlayer.getUsername();
//    Event newTurn;
//    if (currentPlayer.getSkipCounter() == 0) {
//      newTurn = new Event(
//          "It is " + player + "'s turn! " +
//              "If you are " + player +
//              ", move your Progress Tracker up by 1 and hit " + Constants.optionContinue + '.'
//      );
//    } else {
//      newTurn = new Event("You have to skip your turn, " + player + '.');
//    }
//    soutEvent(newTurn);
//  }
//
//  private void announceNewRound() {
//    List<String> buildings = new ArrayList<>(game.getBuildings());
//    String building = buildings.get(new Random().nextInt(buildings.size()));
//    buildings.remove(building);
//    game.setBuildings(new HashSet<>(buildings));
//    Event buildingDestroyed;
//    if (buildings.size() > 1) {
//      buildingDestroyed = new Event("The dragon destroyed the " + building +
//              ". There are " + buildings.size() + " buildings remaining."
//      );
//    } else if (buildings.size() == 1) {
//      buildingDestroyed = new Event("The dragon destroyed the " + building +
//          ". The " + buildings.get(0) + " is the only building remaining."
//      );
//    } else {
//      buildingDestroyed = new Event("The dragon destroyed the " + building +
//          ". With no buildings remaining, the dragon focuses its attention on you!"
//      );
//    }
//    soutEvent(buildingDestroyed);
//  }
//
//  public void soutEvent(Event event) {
//    Set<Option> setOptions = event.getOptions();
//    List<Option> options = null;
//    String prompt = event.getPrompt();
//    String dsPrompt = event.getDsPrompt();
//    Player player = game.getPlayers().size() > 0 && game.getPlayerPointer() >= 0 &&
//        game.getPlayerPointer() < game.getPlayers().size() ?
//        game.getPlayers().get(game.getPlayerPointer()) : null;
//    int choice = -1;
//
//    if (setOptions != null && setOptions.size() > 0) {
//      options = new ArrayList<>(setOptions);
//
//      options.sort(new OptionComparator());
//    }
//
//    // Display Prompt
//    System.out.println(prompt);
//
//    // Display DragonScript Prompt
//    if (dsPrompt != null) {
//      boolean isParsable = false;
//      try {
//        Integer.parseInt(dsPrompt);
//        isParsable = true;
//      } catch (NumberFormatException ignored) {}
//      if (!isParsable) {
//        System.out.println(dsPrompt);
//      }
//    }
//
//    if (options != null) {
//
//      // Display Options
//      for (int i = 0; i < options.size(); i++) {
//        System.out.println(
//            String.format(Constants.fOption, i + 1, options.get(i).getLabel())
//        );
//      }
//
//      choice = console.nextInt();
//
//      if (choice > 0 && choice <= options.size()) {
//        if (prompt.equals(Constants.youAreInATavern)) {
//          Event whatIsYourName = new Event(Constants.whatIsYourName, String.valueOf(choice - 1));
//          soutEvent(whatIsYourName);
//        }
//
//        // OG Event Finish
//        discardCurrentEventCard();
//        soutEvent(options.get(choice - 1).getResult());
//      } else {
//        System.out.println("Choice '" + choice + "' is out of bounds: '1'-'" + options.size() + "'");
//      }
//    } else {
//      if (prompt.equals(Constants.whatIsYourName)) {
//        Player newPlayer = new Player(Integer.parseInt(dsPrompt));
//        console.nextLine();
//        newPlayer.setUsername(console.nextLine());
//        addPlayer(newPlayer);
//        Event welcomePlayer = new Event(
//            String.format(Constants.fWelcomeToTavern, newPlayer.getUsername())
//        );
//        soutEvent(welcomePlayer);
//      } else {
//        System.out.println(String.format(Constants.fOption, 1, Constants.optionContinue));
//        choice = console.nextInt();
//      }
//    }
//    if (prompt.contains(Constants.skipQueue)) {
//      player.setSkipCounter(player.getSkipCounter() + 1);
//    }
////    if (prompt.toLowerCase().contains(Constants.moveQueue)) {
////      if (prompt.toLowerCase().contains(Constants.moveUpQueue)) {
////        if (player.getPosition() < 20) {
////          Event movePlayer;
////          int change = Integer.parseInt(prompt.substring(
////              prompt.indexOf(Constants.moveUpQueue + Constants.moveUpQueue.length()), 1
////          ));
////          player.setPosition(player.getPosition() + change);
////          if (change == 1) {
////            movePlayer = new Event(String.format(
////                Constants.fMoveSingular, player.getUsername(), "up", change,
////                player.getPosition() - change, player.getPosition()
////            ));
////          } else {
////            movePlayer = new Event(String.format(
////                Constants.fMovePlural, player.getUsername(), "up", change,
////                player.getPosition() - change, player.getPosition()
////            ));
////          }
////          soutEvent(movePlayer);
////        }
////      } else if (prompt.toLowerCase().contains(Constants.moveDownQueue)) {
////        if (player.getPosition() > 1) {
////          Event movePlayer;
////          int change = Integer.parseInt(prompt.substring(
////              prompt.indexOf(Constants.moveDownQueue + Constants.moveDownQueue.length()), 1
////          ));
////          player.setPosition(player.getPosition() - change);
////          if (change == 1) {
////            movePlayer = new Event(String.format(
////                Constants.fMoveSingular, player.getUsername(), "down", change,
////                player.getPosition() - change, player.getPosition()
////            ));
////          } else {
////            movePlayer = new Event(String.format(
////                Constants.fMovePlural, player.getUsername(), "down", change,
////                player.getPosition() - change, player.getPosition()
////            ));
////          }
////          soutEvent(movePlayer);
////        }
////      } else {
////        Event badMoveQueue = new Event(
////            "Someone wrote their move queue ambiguously... I can not tell if we need to " +
////                "move your Progress Tracker up or down."
////        );
////        soutEvent(badMoveQueue);
////      }
////    }
//  }
//
//}
