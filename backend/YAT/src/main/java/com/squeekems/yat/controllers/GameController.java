package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Player;
import com.squeekems.yat.entities.Sentence;
import com.squeekems.yat.services.EventService;
import com.squeekems.yat.services.PlayerService;
import com.squeekems.yat.services.SentenceService;
import com.squeekems.yat.util.Constants;
import com.squeekems.yat.util.IntroBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/game")
public class GameController {

  @Autowired
  EventService eventService;
  @Autowired
  IntroBuilder introBuilder;
  @Autowired
  PlayerService playerService;
  @Autowired
  SentenceService sentenceService;
  private List<Long> eventCards;
  private List<Long> players;
  private int playerPointer = 1;

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/start")
  public void start() {
    eventCards = new ArrayList<>();

    for (Event event: eventService.findAll()) {
      if (event.isCard()) {
        eventCards.add(event.getEventId());
      }
    }
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping("/intro")
  public String getIntro() {
    players = new ArrayList<>();

    for (Player player: playerService.findAll()) {
      players.add(player.getPlayerId());
    }
    return introBuilder.getIntro();
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping("/random")
  public Event getRandom() {
    int id = new Random().nextInt(eventCards.size());
    Event event = eventService.getById(eventCards.get(id));

    eventService.delete(event);
    eventCards.remove(id);
    if (eventCards.size() == 0) {
      shuffleDeck();
    }
    return event;
  }

  public Event incrementPlayerPointer() {
    playerPointer += 1;
    if (playerPointer == players.size()) {
      Event newRound = incrementRound();

      if (newRound != null) {
        playerPointer = -1;
        return newRound;
      } else {
        playerPointer = 0;
      }
    }

    Player currentPlayer = playerService.getById(players.get(playerPointer));
    String player = currentPlayer.getUsername();

    if (currentPlayer.getSkipCounter() == 0) {
      return new Event(
          "It is " + player + "'s turn! " + "If you are " + player +
              ", move your Progress Tracker up by 1 and hit " + Constants.optionContinue + '.'
      );
    } else {
      return new Event("You have to skip your turn, " + player + '.');
    }
  }

  private Event incrementRound() {
    List<Sentence> buildings = sentenceService.findAllByFlag("building");
    if (buildings.size() != 0) {
      Sentence building = buildings.get(new Random().nextInt(buildings.size()));
      buildings.remove(building);
      sentenceService.delete(building);
      if (buildings.size() > 1) {
        return new Event("The dragon destroyed the " + building.getSentence() +
            ". There are " + buildings.size() + " buildings remaining."
        );
      } else if (buildings.size() == 1) {
        return new Event("The dragon destroyed the " + building.getSentence() +
            ". The " + buildings.get(0).getSentence() + " is the only building remaining."
        );
      } else {
        return new Event("The dragon destroyed the " + building.getSentence() +
            ". With no buildings remaining, the dragon focuses its attention on you!"
        );
      }
    } else {
      return null;
    }
  }

  private void shuffleDeck() {
    String jDBCDriver = "org.h2.Driver";
    String dbURL = "jdbc:h2:mem:yatpoc";
    String username = "sa";
    String password = "password";
    Connection con = null;
    Statement statement = null;
    try {
      Class.forName(jDBCDriver);
      con = DriverManager.getConnection(dbURL, username, password);
      statement = con.createStatement();
      File file = ResourceUtils.getFile("classpath:eventReload.sql");
      System.out.println("filepath=" + file.toPath());
      String sql = new String(Files.readAllBytes(file.toPath()));
      statement.executeUpdate(sql);
      System.out.println("The deed is done.");
      statement.close();
      con.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
