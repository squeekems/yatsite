package com.squeekems.yat.util.runtime;

import com.squeekems.yat.entities.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Player {

  private Long playerId;
  private String username;
  private Item equipped;
  private List<Item> inventory;
  private Game game;
  private int room;
  private int position;
  private int skipCounter;

  public Player() {
    position = 1;
  }

  public Player(int room) {
    this();
    this.room = room;
  }

  /**
   * Tries to equip the specified item to this player.
   * @param item to be equipped
   * @return true if the item was equipped, false if the item was not equipped
   */
  public boolean equip(Item item) {
    if (!inventory.contains(item) ||
        !item.getType().toLowerCase().contains("weapon")) {
      return false;
    } else {
      equipped = item;
      return true;
    }
  }

  /**
   * Add this player to a list. Sets their id.
   * @param playerList list this player is being added to.
   */
  public void addTo(List<Player> playerList) {
    playerList.add(this);
    this.playerId = (long) playerList.indexOf(this);
  }

  /**
   * Add this player to a game. Sets their id.
   * @param game game this player is being added to.
   */
  public void addTo(Game game) {
    game.addPlayer(this);
  }

  public Long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(Long playerId) {
    this.playerId = playerId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Item getEquipped() {
    return equipped;
  }

  public void setEquipped(Item equipped) {
    this.equipped = equipped;
  }

  public List<Item> getInventory() {
    return inventory;
  }

  public void setInventory(List<Item> inventory) {
    this.inventory = inventory;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public int getRoom() {
    return room;
  }

  public void setRoom(int room) {
    this.room = room;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    if (position > 20) {
      position = 20;
    }
    if (position < 1) {
      position = 1;
    }
    this.position = position;
  }

  public int getSkipCounter() {
    return skipCounter;
  }

  public void setSkipCounter(int skipCounter) {
    this.skipCounter = skipCounter;
  }

  @Override
  public String toString() {
    return "Player{" +
        "playerId=" + playerId +
        ", username='" + username + '\'' +
        ", equipped=" + equipped +
        ", inventory=" + inventory +
        ", game=" + game +
        ", room=" + room +
        ", position=" + position +
        ", skipCounter=" + skipCounter +
        '}';
  }
}

class PlayerComparator implements Comparator<Player> {
  @Override
  public int compare(Player a, Player b) {
    return a.getRoom() - b.getRoom();
  }
}
