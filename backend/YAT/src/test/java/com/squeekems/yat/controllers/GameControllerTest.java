package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Player;
import com.squeekems.yat.entities.Sentence;
import com.squeekems.yat.services.EventService;
import com.squeekems.yat.services.PlayerService;
import com.squeekems.yat.services.SentenceService;
import com.squeekems.yat.util.IntroBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.squeekems.yat.util.Constants.FLAG_SKIP;
import static com.squeekems.yat.util.Constants.SKIP_QUEUE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
  void start() throws Exception {
    // Given:
    when(eventService.findAll()).then(new Answer<List<Event>>() {
      @Override
      public List<Event> answer(InvocationOnMock invocationOnMock) throws Throwable {
        List<Event> events = new ArrayList<>();
        Event event = new Event();
        Event result = new Event();

        event.setEventId(0L);
        event.setCard(true);
        result.setEventId(1L);
        events.add(event);
        events.add(result);

        return events;
      }
    });

    // When:
    ResultActions result = mockMvc.perform(get("/game/start"));

    // Then:
    verify(eventService).findAll();
    verify(sentenceService).findAllByFlag(any(String.class));
    result.andExpect(status().isOk());
    assertNotNull(gameController.eventCards);
    assertNotNull(gameController.buildings);
  }

  @Test
  void getIntro() throws Exception {
    // Given:
    when(playerService.findAll()).then(new Answer<List<Player>>() {
      @Override
      public List<Player> answer(InvocationOnMock invocationOnMock) throws Throwable {
        List<Player> players = new ArrayList<>();
        Player player = new Player();

        player.setPlayerId(0L);
        players.add(player);

        return players;
      }
    });

    // When:
    ResultActions result = mockMvc.perform(get("/game/intro"));

    // Then:
    verify(playerService).findAll();
    verify(introBuilder).getIntro();
    result.andExpect(status().isOk());
    assertNotNull(gameController.players);
  }

  @ParameterizedTest
  @CsvSource({"5, true", "115, true", "115, false"})
  void getEvent(Long id, boolean buildingsIsZero) throws Exception {
    // Given:
    gameController.players = new ArrayList<>();
    gameController.buildings = new ArrayList<>();
    gameController.players.add(0L);
    gameController.players.add(0L);
    if (!buildingsIsZero) {
      gameController.buildings.add(new Sentence());
    }
    when(eventService.getById(any(Long.class))).then(new Answer<Event>() {
      @Override
      public Event answer(InvocationOnMock invocationOnMock) throws Throwable {
        Long eventId = (Long) invocationOnMock.getArgument(0);
        Event event = new Event();
        event.setEventId(eventId);
        event.setPrompt(SKIP_QUEUE);
        return event;
      }
    });
    when(playerService.getById(any(Long.class))).then(new Answer<Player>() {
      @Override
      public Player answer(InvocationOnMock invocationOnMock) throws Throwable {
        Player newPlayer = new Player();

        newPlayer.setPlayerId(invocationOnMock.getArgument(0));

        return newPlayer;
      }
    });

    // When:
    ResultActions result = mockMvc.perform(
        get("/game/event").param("id",id.toString())
    );

    // Then:
    verify(eventService).getById(id);
    verify(playerService).getById(any(Long.class));
    if (id == 115L && buildingsIsZero) {
      verify(eventService).getById(315L);
    }
    result.andExpect(status().isOk());
  }

  @Test
  void getRandom() throws Exception {
    // Given:
    gameController.eventCards = new ArrayList<>();
    gameController.eventCards.add(0L);
    when(eventService.getById(any(Long.class))).then(new Answer<Event>() {
      @Override
      public Event answer(InvocationOnMock invocationOnMock) throws Throwable {
        Event event = new Event();
        
        event.setEventId(0L);

        return event;
      }
    });

    // When:
    ResultActions result = mockMvc.perform(get("/game/random"));

    // Then:
    verify(eventService).getById(any(Long.class));
    verify(eventService).delete(any(Event.class));
    result.andExpect(status().isOk());
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