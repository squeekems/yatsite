package com.squeekems.yat.services;

import com.squeekems.yat.entities.Game;
import com.squeekems.yat.repositories.GameRepository;
import com.squeekems.yat.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class GameService {
  @Autowired
  private GameRepository gameRepository;

  public void save(Game game) {
    System.out.printf(Constants.fSaving + "%n", game);
    gameRepository.save(game);
  }

  public void delete(Game game) {
    System.out.printf(Constants.fDeleting + "%n", game);
    gameRepository.delete(game);
  }

  public void delete(Long gameId) {
    System.out.printf(Constants.fDeleting + "%n", "game(" + gameId + ")");
    gameRepository.deleteById(gameId);
  }

  public Game getById(Long gameId) {
    System.out.printf(Constants.fGettingWithId + "%n", "game", gameId);
    return gameRepository.findById(gameId).orElseThrow();
  }
}
