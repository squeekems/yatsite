package com.squeekems.yat.entities.utilityEntities.flagHandlers;

import com.squeekems.yat.entities.Player;
import com.squeekems.yat.entities.utilityEntities.rewards.Consequence;

public interface FlagHandler {
  boolean conditionMet();
  void giveRewardTo(Player player);
  void givePunishmentTo(Player player);
  String getFlag();
}
