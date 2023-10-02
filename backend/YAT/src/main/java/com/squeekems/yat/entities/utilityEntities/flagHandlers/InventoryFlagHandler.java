package com.squeekems.yat.entities.utilityEntities.flagHandlers;

import com.squeekems.yat.entities.Item;
import com.squeekems.yat.entities.Player;
import com.squeekems.yat.entities.utilityEntities.rewards.Consequence;

import java.util.List;

import static com.squeekems.yat.util.Constants.FLAG_INVENTORY;

public class InventoryFlagHandler implements FlagHandler {

  Consequence reward;
  Consequence punishment;
  Item itemCheck;
  List<Item> inventory;

  @Override
  public boolean conditionMet() {
    return inventory.contains(itemCheck);
  }

  public void giveRewardTo(Player player) {
    reward.giveTo(player);
  }

  public void givePunishmentTo(Player player) {
    punishment.giveTo(player);
  }

  @Override
  public String getFlag() {
    return FLAG_INVENTORY;
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

  public Item getItemCheck() {
    return itemCheck;
  }

  public void setItemCheck(Item itemCheck) {
    this.itemCheck = itemCheck;
  }

  public List<Item> getInventory() {
    return inventory;
  }

  public void setInventory(List<Item> inventory) {
    this.inventory = inventory;
  }
}
