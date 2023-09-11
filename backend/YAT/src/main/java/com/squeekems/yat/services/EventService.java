package com.squeekems.yat.services;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
  @Autowired
  private EventRepository eventRepository;

  public List<Event> findAll() {
    System.out.println("Finding all events");
    return eventRepository.findAll();
  }

  public void save(Event event) {
    System.out.println("Saving \"" + event + "\"");
    eventRepository.save(event);
  }

  public void delete(Event event) {
    System.out.println("Deleting \"" + event + "\"");
    eventRepository.delete(event);
  }

  public Event getEventById(Long eventId) {
    System.out.println("Getting event with id: " + eventId);
    return eventRepository.findById(eventId).orElseThrow();
  }

}
