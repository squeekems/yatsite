package com.squeekems.yat.services;

import com.squeekems.yat.entities.Player;
import com.squeekems.yat.repositories.PlayerRepository;
import com.squeekems.yat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.squeekems.yat.util.Constants.rollDice;

@Service
public class PlayerService {
  Logger log = LoggerFactory.getLogger(PlayerService.class);

  @Autowired
  private PlayerRepository playerRepository;

  public List<Player> findAll() {
    List<Player> players = playerRepository.findAll();
    log.info(String.format(Constants.F_FINDING_ALL, "players", players.size()));
    return players;
  }

  public Player save(Player player) {
    log.info(String.format(Constants.F_SAVING, player));
    return playerRepository.save(player);
  }

  public void delete(Player player) {
    log.info(String.format(Constants.F_DELETING, player));
    playerRepository.delete(player);
  }

  public Player getById(Long playerId) {
    log.info(String.format(Constants.F_GETTING_WITH_ID, "player", playerId));
    return playerRepository.findById(playerId).orElseThrow();
  }

  public void rollSavingThrowFor(Player player) {
    int result = rollDice(1, 20);
    int savingThrowCounter = player.getSavingThrowCounter();

    if (result > savingThrowCounter) {
      player.setSavingThrowCounter(savingThrowCounter + 1);
      save(player);
    } else {
      player.setSavingThrowCounter(0);
      player.setPosition(player.getPosition() - 1);
      player.setInventory(new ArrayList<>());
      save(player);
    }
  }

  public void addSkipTo(Player player) {
    player.setSkipCounter(player.getSkipCounter() + 1);
    save(player);
  }
}
