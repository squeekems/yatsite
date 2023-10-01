package com.squeekems.yat.services;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.repositories.EventRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EventServiceTest {

  @Autowired
  EventService eventService;

  @Test
  void findAll() {
    // Given: Database has rows on events table

    // When:
    List<Event> actual = eventService.findAll();

    // Then:
    assertNotNull(actual);
  }

  @Test
  void save() {
    // Given:
    Event event = new Event();

    // When:
    Event actual = eventService.save(event);

    // Then:
    assertNotNull(actual);
  }

  @Test
  void delete() {
    // Given:
    Long eventId = 1L;
    String dsPrompt = null;
    boolean isCard = false;
    String prompt = "Roll a D20 to attempt. If the attempt roll is more than 10, you get away " +
        "with something from his cart. Draw a card from the Greater Loot pile. If the attempt " +
        "roll is no greater than 10, the merchant lands a decisive blow to your head. Roll a " +
        "Saving Throw.";
    Event event1 = new Event(eventId, dsPrompt, isCard, prompt);

    // When:
    eventService.delete(event1);

    // Then:

  }

  @Test
  void deleteById() {
    // Given:
    Long id = 1L;

    // When:
    eventService.deleteById(id);

    // Then:
  }

  @Test
  void getById() {
    // Given:
    Long id = 1L;

    // When:
    Event actual = eventService.getById(5L);

    // Then:
    assertNotNull(actual);
  }

  @Test
  void dropTable() {
    // Given:

    // When:

    // Then:
  }
}