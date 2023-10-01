package com.squeekems.yat.services;

import com.squeekems.yat.entities.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayerServiceTest {

  @Autowired
  PlayerService playerService;

  @Test
  void findAll() {
  }

  @Test
  void save() {
    // Given:
    Player player = new Player();

    // When:
    Player actual = playerService.save(player);

    // Then:
    assertNotNull(actual);
  }

  @Test
  void delete() {
  }

  @Test
  void getById() {
  }
}