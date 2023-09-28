package com.squeekems.yat.util.comparators;

import com.squeekems.yat.entities.Player;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayerComparatorTest {

  private final PlayerComparator playerComparator = new PlayerComparator();

  @Test
  void compareTestEqual() {
    Player a = new Player();
    Player b = new Player();
    a.setRoom(0);
    b.setRoom(0);
    int result = playerComparator.compare(a, b);
    assertEquals(0, result);
  }

  @Test
  void compareTestGreater() {
    Player a = new Player();
    Player b = new Player();
    a.setRoom(1);
    b.setRoom(0);
    int result = playerComparator.compare(a, b);
    assert(result > 0);
  }

  @Test
  void compareTestLesser() {
    Player a = new Player();
    Player b = new Player();
    a.setRoom(0);
    b.setRoom(1);
    int result = playerComparator.compare(a, b);
    assert(result < 0);
  }

}