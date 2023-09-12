package com.squeekems.yat.util.runtime;

import com.squeekems.yat.YatApplication;
import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Game {

  Long roomId;
  List<String> buildings;
  int dragonHitPoints;
  int playerPointer;
  Event currentEvent;
  List<Player> players;
  List<Event> eventDeck;
  List<Event> eventDiscard;
  List<Item> greaterLoot;
  List<Item> lesserLoot;
  List<Item> lootDiscard;
  String theStory;

  public static void main(String[] args) {
    SpringApplication.run(YatApplication.class, args);
  }

  private Game() {
    buildings = new ArrayList<>(Arrays.asList("tavern", "farmstead", "butchery", "jail",
        "lumber mill", "fletcher", "gypsy cottage", "blacksmith", "barber", "greenhouse",
        "apothecary", "charm chamber", "barracks", "bakery", "library", "cobbler", "tailor",
        "bathhouse", "church", "town hall"));
    dragonHitPoints = 0;
    playerPointer = 0;
  }

  public Game(Player... players) {
    this();
    this.players = new ArrayList<>(Arrays.asList(players));
    dragonHitPoints = this.players.size() * 20 + 20;
  }

  public void addPlayer(Player player) {
    players.add(player);
    player.setPlayerId((long) players.indexOf(player));
    dragonHitPoints += 20;
  }

  public void soutEvent(Event event) {

  }

}
