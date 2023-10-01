package com.squeekems.yat.services;

import com.squeekems.yat.entities.Player;
import com.squeekems.yat.repositories.PlayerRepository;
import com.squeekems.yat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
  Logger log = LoggerFactory.getLogger(PlayerService.class);

  @Autowired
  private PlayerRepository playerRepository;

  public List<Player> findAll() {
    log.info(String.format(Constants.fFindingAll + "%n", "players"));
    return playerRepository.findAll();
  }

  public Player save(Player player) {
    log.info(String.format(Constants.fSaving + "%n", player));
    return playerRepository.save(player);
  }

  public void delete(Player player) {
    log.info(String.format(Constants.fDeleting + "%n", player));
    playerRepository.delete(player);
  }

  public Player getById(Long playerId) {
    log.info(String.format(Constants.fGettingWithId + "%n", "player", playerId));
    return playerRepository.findById(playerId).orElseThrow();
  }
}
