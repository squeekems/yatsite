package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Player;
import com.squeekems.yat.entities.Sentence;
import com.squeekems.yat.services.EventService;
import com.squeekems.yat.services.PlayerService;
import com.squeekems.yat.services.SentenceService;
import com.squeekems.yat.util.Constants;
import com.squeekems.yat.util.IntroBuilder;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.sql.*;
import java.util.*;

import static com.squeekems.yat.util.Constants.*;

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
  List<Sentence> buildings;

  @CrossOrigin(origins = CORS_URL)
  @GetMapping("/start")
  public void start() {
    eventCards = new ArrayList<>();
    buildings = sentenceService.findAllByFlag("building");

    for (Event event: eventService.findAll()) {
      if (event.isCard()) {
        eventCards.add(event.getEventId());
      }
    }
  }

  @CrossOrigin(origins = CORS_URL)
  @RequestMapping("/intro")
  public String getIntro() {
    players = new ArrayList<>();

    for (Player player: playerService.findAll()) {
      players.add(player.getPlayerId());
    }
    return introBuilder.getIntro();
  }

  @CrossOrigin(origins = CORS_URL)
  @GetMapping("/event")
  public Event getEvent(@RequestParam Long id) {
    Event event = eventService.getById(id);
    // if this event is a result
    if (!event.isCard()) {
      // if "Skip your next turn."
      if (event.getPrompt().contains(SKIP_QUEUE)) {
        Player player = playerService.getById(players.get(playerPointer));
        player.setSkipCounter(player.getSkipCounter() + 1);
        playerService.save(player);
      }
    }
    // if id=115 or "Move the Buildings Remaining Tracker down by 1."
    if (id == 115L) {
      if (buildings.size() == 0) {
        return eventService.getById(315L);
      } else {
        Event buildingDestroyed = destroyBuilding();
        event.setPrompt(event.getPrompt() + ' ' + buildingDestroyed.getPrompt());
      }
    }
    return event;
  }

  @CrossOrigin(origins = CORS_URL)
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

  @CrossOrigin(origins = CORS_URL)
  @RequestMapping("/dragon")
  public Event getDragon() {
    return eventService.getById(314L);
  }

  @CrossOrigin(origins = CORS_URL)
  @RequestMapping("/increment-turn")
  public Event incrementPlayerPointer() {
    playerPointer += 1;
    if (playerPointer > players.size()) {
      Event buildingDestroyed = null;
      if (buildings.size() >= 1) {
        buildingDestroyed = destroyBuilding();
      }

      playerPointer = 1;

      return buildingDestroyed;
    }

    Player currentPlayer = playerService.getById(players.get(playerPointer));
    String player = currentPlayer.getUsername();

    if (currentPlayer.getSkipCounter() <= 0) {
      currentPlayer.setSkipCounter(0);
      playerService.save(currentPlayer);
      return new Event(
          "It is " + player + "'s turn! " + "If you are " + player +
              ", move your Progress Tracker up by 1 and hit continue..."
      );
    } else {
      currentPlayer.setSkipCounter(currentPlayer.getSkipCounter() - 1);
      playerService.save(currentPlayer);
      return new Event("You have to skip your turn, " + player + '.');
    }
  }

  @CrossOrigin(origins = CORS_URL)
  @RequestMapping("/newGame")
  public void newGame() {
    Connection con = null;
    Statement statement = null;
    try {
      Class.forName(jDBCDriver);
      con = DriverManager.getConnection(dbURL, username, password);
      statement = con.createStatement();
      PreparedStatement setRefIntegrityFalse = con.prepareStatement("SET REFERENTIAL_INTEGRITY FALSE");
      PreparedStatement setRefIntegrityTrue = con.prepareStatement("SET REFERENTIAL_INTEGRITY TRUE");
      setRefIntegrityFalse.execute();
      ResultSet tables = con.createStatement().executeQuery("SHOW TABLES");
      while (tables.next()){
        System.out.println(tables.getString(1));
        statement.execute("DELETE FROM " + tables.getString(1));
      }
      setRefIntegrityTrue.execute();
      File file = ResourceUtils.getFile("classpath:data.sql");
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

  @GetMapping("/roll")
  public int rollDice(@RequestParam int number, @RequestParam int sides) {
    return Constants.rollDice(number, sides);
  }

  @GetMapping("/rollExplicit")
  public List<Integer> rollDiceExplicit(@RequestParam int number, @RequestParam int sides) {
    return Constants.rollDiceExplicit(number, sides);
  }

  /**
   * Roll your stats by rolling 4 x 6-sided dice, dropping the lowest number, and adding the
   * remaining total. Do this for each stat (there are 6). Alternatively, calculate your stats
   * using the Point Buy or Standard Array, which don't require rolling and limit variation in
   * character stats.
   * @return map of 6 lists of dice roll results each list having 4 die results and the total
   * minus the lowest result
   */
  @GetMapping("/rollCharacter")
  public Map<Integer, List<Integer>> rollCharacter() {
    Map<Integer, List<Integer>> stats = new HashMap<>();
    int number = 4;
    int sides = 6;

    // For each stat (There are 6)
    for (int i = 0; i < sides; i++) {

      // Roll 4 dice
      List<Integer> stat = rollDiceExplicit(number, sides);

      // Sort lowest to highest
      Collections.sort(stat);

      // Reusable
      int lowestValue = stat.get(0);

      // Drop the lowest number from the total
      stat.set(number, stat.get(number) - lowestValue);

      // Make the lowest value negative for display purposes
      stat.set(0, lowestValue * -1);

      // Key is just to number our lists
      stats.put(i + 1, stat);
    }

    return stats;
  }

  private Event destroyBuilding() {
    Sentence building = new Sentence();
    if (buildings.size() != 0) {
      building = buildings.get(new Random().nextInt(buildings.size()));
      buildings.remove(building);
      sentenceService.delete(building);
    }
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

  }

  private void shuffleDeck() {
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
