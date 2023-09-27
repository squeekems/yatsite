package com.squeekems.yat.util.comparators;

import com.squeekems.yat.entities.Player;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
  @Override
  public int compare(Player a, Player b) {
    return a.getRoom() - b.getRoom();
  }
}
