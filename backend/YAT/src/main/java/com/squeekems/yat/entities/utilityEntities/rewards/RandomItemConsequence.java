//package com.squeekems.yat.entities.utilityEntities.rewards;
//
//import com.squeekems.yat.entities.Item;
//import com.squeekems.yat.entities.Player;
//
//import java.util.List;
//import java.util.Random;
//
//public class RandomItemConsequence implements Consequence {
//  List<Item> reward;
//
//  @Override
//  public void giveTo(Player player) {
//    List<Item> inventory = player.getInventory();
//    inventory.add(reward.get(new Random().nextInt(reward.size())));
//    player.setInventory(inventory);
//  }
//}
