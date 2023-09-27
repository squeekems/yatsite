package com.squeekems.yat.services;

import com.squeekems.yat.entities.Player;
import com.squeekems.yat.repositories.PlayerRepository;
import com.squeekems.yat.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
  @Autowired
  private PlayerRepository playerRepository;

  public List<Player> findAll() {
    System.out.printf(Constants.fFindingAll + "%n", "events");
    return playerRepository.findAll();
  }

  public void save(Player player) {
    System.out.printf(Constants.fSaving + "%n", player);
    playerRepository.save(player);
  }
  public void delete(Player player) {
    System.out.printf(Constants.fDeleting + "%n", player);
    playerRepository.delete(player);
  }

  public Player getById(Long playerId) {
    System.out.printf(Constants.fGettingWithId + "%n", "player", playerId);
    return playerRepository.findById(playerId).orElseThrow();
  }
}
