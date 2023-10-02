package com.squeekems.yat.services;

import com.squeekems.yat.entities.Player;
import com.squeekems.yat.repositories.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class PlayerServiceTest {

  @Mock
  PlayerRepository playerRepository;
  @InjectMocks
  PlayerService playerService;

  @Test
  void findAll() {
    // Given:
    when(playerRepository.findAll()).then(new Answer<List<Player>>() {
      @Override
      public List<Player> answer(InvocationOnMock invocationOnMock) throws Throwable {
        return new ArrayList<Player>();
      }
    });

    // When:
    List<Player> actual = playerService.findAll();

    // Then:
    assertNotNull(actual);
  }

  @Test
  void save() {
    // Given:
    Player player = new Player();
    when(playerRepository.save(any(Player.class))).then(new Answer<Player>() {
      Long sequence = 1L;
      @Override
      public Player answer(InvocationOnMock invocationOnMock) throws Throwable {
        Player newPlayer = (Player) invocationOnMock.getArgument(0);
        newPlayer.setPlayerId(sequence++);
        return newPlayer;
      }
    });

    // When:
    Player actual = playerService.save(player);

    // Then:
    verify(playerRepository).save(player);
    assertNotNull(actual);
  }

  @Test
  void delete() {
    // Given:
    Player player = new Player();

    // When:
    playerService.delete(player);

    // Then:
    verify(playerRepository).delete(player);
  }

  @Test
  void getById() {
    // Given:
    Long id = 1L;
    when(playerRepository.findById(any(Long.class))).then(new Answer<Optional<Player>>() {
      @Override
      public Optional<Player> answer(InvocationOnMock invocationOnMock) throws Throwable {
        Long playerId = (Long) invocationOnMock.getArgument(0);
        Player player = new Player();
        player.setPlayerId(playerId);
        return Optional.of(player);
      }
    });

    // When:
    Player actual = playerService.getById(id);

    // Then:
    verify(playerRepository).findById(id);
    assertNotNull(actual);
  }
}