package com.squeekems.yat.services;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.repositories.EventRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EventServiceTest {

  @Autowired
  EventRepository eventRepository;
  @Autowired
  EventService eventService;

  @Test
  void findAll() {
    List<Event> events = eventService.findAll();
    long expected = eventRepository.count();
    long actual = events.size();

    System.out.println("expected=" + expected);
    System.out.println("actual=" + actual);

    assertEquals(expected, actual);
  }

  @Test
  void save() {
//    Event expected = new Event();
//    eventService.save(expected);
//    Event actual = eventService.getById(expected.getEventId());
//    assertEquals(expected.getPrompt(), actual.getPrompt());
  }

  @Test
  void delete() {
    List<Event> events = new ArrayList<>();
    events.add(new Event("0"));
    events.add(new Event("1"));
    events.add(new Event("2"));
    events.add(new Event("3"));
    System.out.println("size=" + events.size());
    System.out.println(events.get(0));
    System.out.println(events.get(3));
    int eventPointer = -1;

  }

  @Test
  void deleteById() {
  }

  @Test
  void getById() {
    Event event = eventService.getById(5L);
    System.out.println(event);
  }

  @Test
  void dropTable() {
  }
}