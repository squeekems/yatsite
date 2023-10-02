package com.squeekems.yat.controllers;

import com.squeekems.yat.services.EventService;
import com.squeekems.yat.services.PlayerService;
import com.squeekems.yat.services.SentenceService;
import com.squeekems.yat.util.IntroBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class GameControllerTest {

  @Mock
  EventService eventService;
  @Mock
  IntroBuilder introBuilder;
  @Mock
  PlayerService playerService;
  @Mock
  SentenceService sentenceService;
  @InjectMocks
  GameController gameController;
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
  }

  @Test
  void start() {
  }

  @Test
  void getIntro() {
  }

  @Test
  void getEvent() {
  }

  @Test
  void getRandom() {
  }

  @Test
  void getDragon() {
  }

  @Test
  void incrementPlayerPointer() {
  }

  @Test
  void newGame() {
  }

//  @ParameterizedTest
//  @CsvSource({"1, 20", "2, 6"})
//  void rollDice(int number, int sides) {
//    // Given:
//    // Parameters
//
//    // When:
//    int actual = rollDice(number, sides);
//    System.out.println("Given: number: " + number + " sides: " + sides + " actual: " + actual);
//
//    // Then:
//    assertTrue(actual >= number);
//    assertTrue(actual <= sides * number);
//  }
}