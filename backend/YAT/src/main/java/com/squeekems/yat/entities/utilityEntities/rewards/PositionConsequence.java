package com.squeekems.yat.entities.utilityEntities.rewards;

import com.squeekems.yat.entities.Player;

public class PositionConsequence implements Consequence {
  int reward;

  @Override
  public void giveTo(Player player) {
    player.setPosition(player.getPosition() + reward);
  }
}
