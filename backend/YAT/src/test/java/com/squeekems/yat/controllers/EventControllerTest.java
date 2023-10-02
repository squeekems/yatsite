package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Option;
import com.squeekems.yat.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EventControllerTest {
  @Mock
  EventService eventService;
  @InjectMocks
  EventController eventController;
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
  }

  @Test
  void getEvents() throws Exception {
    // Given:

    // When:
    ResultActions result = mockMvc.perform(get("/events"));

    // Then:
    verify(eventService).findAll();
    result.andExpect(status().isOk());
  }

  @Test
  void getEvent() throws Exception {
    // Given:
    Long id = 1L;
    when(eventService.getById(any(Long.class))).then(new Answer<Event>() {
      @Override
      public Event answer(InvocationOnMock invocationOnMock) throws Throwable {
        Long eventId = (Long) invocationOnMock.getArgument(0);
        Event event = new Event();
        event.setEventId(eventId);
        return event;
      }
    });

    // When:
    ResultActions result = mockMvc.perform(
        get("/events/event").param("id", id.toString())
    );

    // Then:
    verify(eventService).getById(id);
    result.andExpect(status().isOk());
  }

  @Test
  void getCardsWhenDbHasEvents() throws Exception {
    // Given:
    when(eventService.findAll()).then(new Answer<List<Event>>() {
      @Override
      public List<Event> answer(InvocationOnMock invocationOnMock) throws Throwable {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
          Event event = new Event();
          event.setCard(i == 1);
          events.add(event);
        }
        return events;
      }
    });

    // When:
    ResultActions result = mockMvc.perform(get("/events/cards"));

    // Then:
    verify(eventService).findAll();
    result.andExpect(status().isOk());
  }

  @Test
  void getCardsWhenDbHasNoEvents() throws Exception {
    // Given:
    when(eventService.findAll()).then(new Answer<List<Event>>() {
      @Override
      public List<Event> answer(InvocationOnMock invocationOnMock) throws Throwable {
        return new ArrayList<>();
      }
    });

    // When:
    ResultActions result = mockMvc.perform(get("/events/cards"));

    // Then:
    verify(eventService).findAll();
    result.andExpect(status().isOk());
  }

  @Test
  void getCardWhenIdIsValid() throws Exception {
    // Given:
    Long id = 1L;

    when(eventService.getById(any(Long.class))).then(new Answer<Event>() {
      @Override
      public Event answer(InvocationOnMock invocationOnMock) throws Throwable {
        Long eventId = (Long) invocationOnMock.getArgument(0);
        Event event = new Event();
        Long resultId = 2L;
        Option option = new Option();
        Set<Option> options = new HashSet<>();

        option.setResultId(resultId);

        options.add(option);

        event.setEventId(eventId);
        event.setCard(true);
        event.setOptions(options);
        return event;
      }
    });

    // When:
    ResultActions result = mockMvc.perform(get("/events/card")
        .param("id", id.toString())
    );

    // Then:
    verify(eventService).getById(id);
    result.andExpect(status().isOk());
  }

  @Test
  void getCardWhenIdIsNotValid() throws Exception {
    // Given:
    Long id = 1L;

    when(eventService.getById(any(Long.class))).then(new Answer<Event>() {
      @Override
      public Event answer(InvocationOnMock invocationOnMock) throws Throwable {
        Long eventId = (Long) invocationOnMock.getArgument(0);
        Event event = new Event();
        event.setEventId(eventId);
        return event;
      }
    });

    // When:
    ResultActions result = mockMvc.perform(get("/events/card")
        .param("id", id.toString())
    );

    // Then:
    verify(eventService).getById(id);
    result.andExpect(status().isOk());
  }

  @Test
  void getResultsWhenDbHasEvents() throws Exception {
    // Given:
    when(eventService.findAll()).then(new Answer<List<Event>>() {
      @Override
      public List<Event> answer(InvocationOnMock invocationOnMock) throws Throwable {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
          Event event = new Event();
          event.setCard(i == 1);
          events.add(event);
        }
        return events;
      }
    });

    // When:
    ResultActions result = mockMvc.perform(get("/events/results"));

    // Then:
    verify(eventService).findAll();
    result.andExpect(status().isOk());
  }

  @Test
  void getResultsWhenDbHasNoEvents() throws Exception {
    // Given:
    when(eventService.findAll()).then(new Answer<List<Event>>() {
      @Override
      public List<Event> answer(InvocationOnMock invocationOnMock) throws Throwable {
        return new ArrayList<>();
      }
    });

    // When:
    ResultActions result = mockMvc.perform(get("/events/results"));

    // Then:
    verify(eventService).findAll();
    result.andExpect(status().isOk());
  }

  @Test
  void getDSCardsWhenDbHasEvents() throws Exception {
    // Given:
    when(eventService.findAll()).then(new Answer<List<Event>>() {
      @Override
      public List<Event> answer(InvocationOnMock invocationOnMock) throws Throwable {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
          Event event = new Event();
          event.setDsPrompt(i == 1 ? "getDSCardsWhenDbHasEvents" : null);
          events.add(event);
        }
        return events;
      }
    });

    // When:
    ResultActions result = mockMvc.perform(get("/events/dsCards"));

    // Then:
    verify(eventService).findAll();
    result.andExpect(status().isOk());
  }

  @Test
  void getDSCardsWhenDbHasNoEvents() throws Exception {
    // Given:
    when(eventService.findAll()).then(new Answer<List<Event>>() {
      @Override
      public List<Event> answer(InvocationOnMock invocationOnMock) throws Throwable {
        return new ArrayList<>();
      }
    });

    // When:
    ResultActions result = mockMvc.perform(get("/events/dsCards"));

    // Then:
    verify(eventService).findAll();
    result.andExpect(status().isOk());
  }

  @Test
  void postEvent() throws Exception {
    // Given:

    // When:
    ResultActions result = mockMvc.perform(
        post("/events/event").param("prompt", "testPrompt")
    );

    // Then:
    verify(eventService).save(any(Event.class));
    result.andExpect(status().isOk());
  }

  @Test
  void deleteById() throws Exception {
    // Given:
    Long id = 1L;

    // When:
    ResultActions result = mockMvc.perform(
        delete("/events/event").param("id", id.toString())
    );

    // Then:
    verify(eventService).deleteById(id);
    result.andExpect(status().isOk());
  }
}