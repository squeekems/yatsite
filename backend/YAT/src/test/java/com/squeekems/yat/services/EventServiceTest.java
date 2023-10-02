package com.squeekems.yat.services;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.repositories.EventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class EventServiceTest {

  @Mock
  EventRepository eventRepository;
  @InjectMocks
  EventService eventService;

  @Test
  void findAll() {
    // Given:
    when(eventRepository.findAll()).then(new Answer<List<Event>>() {
      @Override
      public List<Event> answer(InvocationOnMock invocationOnMock) throws Throwable {
        return new ArrayList<Event>();
      }
    });

    // When:
    List<Event> actual = eventService.findAll();

    // Then:
    assertNotNull(actual);
  }

  @Test
  void save() {
    // Given:
    Event event = new Event();
    when(eventRepository.save(any(Event.class))).then(new Answer<Event>() {
      Long sequence = 1L;
      @Override
      public Event answer(InvocationOnMock invocationOnMock) throws Throwable {
        Event newEvent = (Event) invocationOnMock.getArgument(0);
        newEvent.setEventId(sequence++);
        return newEvent;
      }
    });

    // When:
    Event actual = eventService.save(event);

    // Then:
    verify(eventRepository).save(event);
    assertNotNull(actual);
  }

  @Test
  void delete() {
    // Given:
    Event event = new Event();

    // When:
    eventService.delete(event);

    // Then:
    verify(eventRepository).delete(event);
  }

  @Test
  void deleteById() {
    // Given:
    Long id = 1L;

    // When:
    eventService.deleteById(id);

    // Then:
    verify(eventRepository).deleteById(id);
  }

  @Test
  void getById() {
    // Given:
    Long id = 1L;
    when(eventRepository.findById(any(Long.class))).then(new Answer<Optional<Event>>() {
      @Override
      public Optional<Event> answer(InvocationOnMock invocationOnMock) throws Throwable {
        Long eventId = (Long) invocationOnMock.getArgument(0);
        Event event = new Event();
        event.setEventId(eventId);
        return Optional.of(event);
      }
    });

    // When:
    Event actual = eventService.getById(id);

    // Then:
    verify(eventRepository).findById(id);
    assertNotNull(actual);
  }
}