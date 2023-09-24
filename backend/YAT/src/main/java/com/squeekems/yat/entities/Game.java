package com.squeekems.yat.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.squeekems.yat.util.Constants;
import com.squeekems.yat.util.comparators.PlayerComparator;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "games")
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long gameId;
  @Column
  private int dragonHitPoints;
  @Column
  private int playerPointer;
  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "eventId", referencedColumnName = "eventId")
  private Event currentEvent;
  @ElementCollection
  private Set<String> buildings;
  @ManyToMany(targetEntity = Event.class, fetch = FetchType.EAGER)
  @JsonManagedReference
  @JoinTable( name = "game_deck", joinColumns = { @JoinColumn(name = "gameId") },
      inverseJoinColumns = { @JoinColumn(name = "eventId") })
  private Set<Event> deck;
  @ManyToMany(targetEntity = Event.class, fetch = FetchType.EAGER)
  @JsonManagedReference
  @JoinTable( name = "game_discard", joinColumns = { @JoinColumn(name = "gameId") },
      inverseJoinColumns = { @JoinColumn(name = "eventId") })
  private Set<Event> discard;
  @ManyToMany(targetEntity = Player.class, fetch = FetchType.EAGER)
  @JsonManagedReference
  @JoinTable( name = "game_players", joinColumns = { @JoinColumn(name = "gameId") },
      inverseJoinColumns = { @JoinColumn(name = "playerId") })
  private List<Player> players;

  public Game() {
    buildings = new HashSet<>(Arrays.asList(Constants.buildings));
    dragonHitPoints = 0;
    players = new ArrayList<>();
    deck = new HashSet<>();
    discard = new HashSet<>();
    playerPointer = -1;
  }

  public Long getGameId() {
    return gameId;
  }

  public void setGameId(Long gameId) {
    this.gameId = gameId;
  }

  public int getDragonHitPoints() {
    return dragonHitPoints;
  }

  public void setDragonHitPoints(int dragonHitPoints) {
    this.dragonHitPoints = dragonHitPoints;
  }

  public int getPlayerPointer() {
    return playerPointer;
  }

  public void setPlayerPointer(int playerPointer) {
    this.playerPointer = playerPointer;
  }

  public Event getCurrentEvent() {
    return currentEvent;
  }

  public void setCurrentEvent(Event currentEvent) {
    this.currentEvent = currentEvent;
  }

  public Set<String> getBuildings() {
    return buildings;
  }

  public void setBuildings(Set<String> buildings) {
    this.buildings = buildings;
  }

  public Set<Event> getDeck() {
    return deck;
  }

  public void setDeck(Set<Event> deck) {
    this.deck = deck;
  }

  public Set<Event> getDiscard() {
    return discard;
  }

  public void setDiscard(Set<Event> discard) {
    this.discard = discard;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public void addPlayer(Player player) {
    this.players.add((player));
    player.setGame(this);
    this.dragonHitPoints += 20;
  }

  public void sortPlayers() {
    this.players.sort(new PlayerComparator());
  }

  @Override
  public String toString() {
    return "Game{" +
        "gameId=" + gameId +
        ", dragonHitPoints=" + dragonHitPoints +
        ", playerPointer=" + playerPointer +
        ", currentEvent=" + currentEvent +
        ", buildings=" + buildings +
        ", eventDeck=" + deck +
        ", eventDiscard=" + discard +
        ", players=" + players +
        '}';
  }
}
