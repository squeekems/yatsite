package com.squeekems.yat.entities.utilityEntities.flagHandlers;

import com.squeekems.yat.entities.Player;
import com.squeekems.yat.entities.utilityEntities.rewards.Consequence;
import com.squeekems.yat.util.Constants;

import static com.squeekems.yat.util.Constants.FLAG_ROLL;

public class RollFlagHandler implements FlagHandler {
  Consequence reward;
  Consequence punishment;
  int rollGoal;
  int numberOfDice;
  int sidesPerDie;

  @Override
  public boolean conditionMet() {
    return Constants.rollDice(numberOfDice, sidesPerDie) >= rollGoal;
  }

  public void giveRewardTo(Player player) {
    reward.giveTo(player);
  }

  public void givePunishmentTo(Player player) {
    punishment.giveTo(player);
  }

  @Override
  public String getFlag() {
    return FLAG_ROLL;
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

  public int getRollGoal() {
    return rollGoal;
  }

  public void setRollGoal(int rollGoal) {
    this.rollGoal = rollGoal;
  }

  public int getNumberOfDice() {
    return numberOfDice;
  }

  public void setNumberOfDice(int numberOfDice) {
    this.numberOfDice = numberOfDice;
  }

  public int getSidesPerDie() {
    return sidesPerDie;
  }

  public void setSidesPerDie(int sidesPerDie) {
    this.sidesPerDie = sidesPerDie;
  }
}
