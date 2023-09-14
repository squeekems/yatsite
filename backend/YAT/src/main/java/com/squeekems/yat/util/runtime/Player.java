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

  public Player() {
  }

  public Player(int room) {
    this.room = room;
  }

  public Player(String username) {
    this.username = username;
  }

  public Player(String username, int room) {
    this(username);
    this.room = room;
  }

  public Player(String username, Item... inventory) {
    this(username);
    this.inventory = new ArrayList<>(Arrays.asList(inventory));
    for (Item item: this.inventory) {
      if (equip(item)) {
        break;
      }
    }
  }

  public Player(String username, int room, Item... inventory) {
    this(username, inventory);
    this.room = room;
  }

  public Player(String username, Item equipped, List<Item> inventory) {
    this(username);
    this.equipped = equipped;
    this.inventory = inventory;
  }

  public Player(String username, int room, Item equipped, List<Item> inventory) {
    this(username, equipped, inventory);
    this.room = room;
  }

  public Player(String username, Game game, Item equipped, List<Item> inventory) {
    this(username, equipped, inventory);
    this.game = game;
  }

  public Player(String username, int room, Game game, Item equipped, List<Item> inventory) {
    this(username, game, equipped, inventory);
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
    playerId = (long) playerList.indexOf(this);
  }

  /**
   * Add this player to a game. Sets their id.
   * @param game game this player is being added to.
   */
  public void addTo(Game game) {
    game.addPlayer(this);
    this.game = game;
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

  @Override
  public String toString() {
    return "Player{" +
        "playerId=" + playerId +
        ", username='" + username + '\'' +
        ", equipped=" + equipped +
        ", inventory=" + inventory +
        ", game=" + game +
        ", room=" + room +
        '}';
  }
}

class PlayerComparator implements Comparator<Player> {
  @Override
  public int compare(Player a, Player b) {
    return a.getRoom() - b.getRoom();
  }
}
