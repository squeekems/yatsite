package com.squeekems.yat.util.runtime;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Item;

import java.util.*;

public class Room {

  Long roomId;
  int buildingsRemaining;
  int dragonHitPoints;
  List<Player> players;
  List<Event> eventDeck;
  List<Event> eventDiscard;
  List<Item> greaterLoot;
  List<Item> lesserLoot;
  List<Item> lootDiscard;

  private Room() {
    buildingsRemaining = 20;
  }

  public Room(Player... players) {
    this();
    this.players = new ArrayList<>(Arrays.asList(players));
    dragonHitPoints = this.players.size() * 20 + 20;
  }

  public void addPlayer(Player player) {
    players.add(player);
    player.setPlayerId((long) players.indexOf(player));
  }

  public Long getRoomId() {
    return roomId;
  }

  public void setRoomId(Long roomId) {
    this.roomId = roomId;
  }

  public int getBuildingsRemaining() {
    return buildingsRemaining;
  }

  public void setBuildingsRemaining(int buildingsRemaining) {
    this.buildingsRemaining = buildingsRemaining;
  }

  public int getDragonHitPoints() {
    return dragonHitPoints;
  }

  public void setDragonHitPoints(int dragonHitPoints) {
    this.dragonHitPoints = dragonHitPoints;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public List<Event> getEventDeck() {
    return eventDeck;
  }

  public void setEventDeck(List<Event> eventDeck) {
    this.eventDeck = eventDeck;
  }

  public List<Event> getEventDiscard() {
    return eventDiscard;
  }

  public void setEventDiscard(List<Event> eventDiscard) {
    this.eventDiscard = eventDiscard;
  }

  public List<Item> getGreaterLoot() {
    return greaterLoot;
  }

  public void setGreaterLoot(List<Item> greaterLoot) {
    this.greaterLoot = greaterLoot;
  }

  public List<Item> getLesserLoot() {
    return lesserLoot;
  }

  public void setLesserLoot(List<Item> lesserLoot) {
    this.lesserLoot = lesserLoot;
  }

  public List<Item> getLootDiscard() {
    return lootDiscard;
  }

  public void setLootDiscard(List<Item> lootDiscard) {
    this.lootDiscard = lootDiscard;
  }

  @Override
  public String toString() {
    return "Room{" +
        "roomId=" + roomId +
        ", buildingsRemaining=" + buildingsRemaining +
        ", dragonHitPoints=" + dragonHitPoints +
        ", players=" + players +
        ", eventDeck=" + eventDeck +
        ", eventDiscard=" + eventDiscard +
        ", greaterLoot=" + greaterLoot +
        ", lesserLoot=" + lesserLoot +
        ", lootDiscard=" + lootDiscard +
        '}';
  }

}
