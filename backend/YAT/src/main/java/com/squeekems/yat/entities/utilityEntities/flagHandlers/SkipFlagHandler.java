package com.squeekems.yat.entities.utilityEntities.flagHandlers;

import com.squeekems.yat.entities.Player;
import com.squeekems.yat.entities.utilityEntities.rewards.Consequence;

import static com.squeekems.yat.util.Constants.FLAG_SKIP;

public class SkipFlagHandler implements FlagHandler {
  Consequence reward;
  Consequence punishment;

  @Override
  public boolean conditionMet() {
    return true;
  }

  public void giveRewardTo(Player player) {
    reward.giveTo(player);
  }

  public void givePunishmentTo(Player player) {
    punishment.giveTo(player);
  }

  @Override
  public String getFlag() {
    return FLAG_SKIP;
  }

  public Consequence getReward() {
    return reward;
  }

  public void setReward(Consequence reward) {
    this.reward = reward;
  }

  public Consequence getPunishment() {
    return punishment;
  }

  public void setPunishment(Consequence punishment) {
    this.punishment = punishment;
  }
}
