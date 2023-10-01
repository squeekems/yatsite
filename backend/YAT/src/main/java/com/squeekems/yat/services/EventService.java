package com.squeekems.yat.services;

import com.squeekems.yat.controllers.EventController;
import com.squeekems.yat.entities.Event;
import com.squeekems.yat.repositories.EventRepository;
import com.squeekems.yat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
  Logger log = LoggerFactory.getLogger(EventController.class);

  @Autowired
  private EventRepository eventRepository;

  public List<Event> findAll() {
    List<Event> events = eventRepository.findAll();
    log.info(String.format(Constants.F_FINDING_ALL + "%n", "events", events.size()));
    return events;
  }

  public Event save(Event event) {
    log.info(String.format(Constants.F_SAVING + "%n", event));
    return eventRepository.save(event);
  }

  public void delete(Event event) {
    log.info(String.format(Constants.F_DELETING + "%n", event));
    eventRepository.delete(event);
  }

  public void deleteById(Long id) {
    log.info(String.format(Constants.F_DELETING + "%n", "event(" + id + ")"));
    eventRepository.deleteById(id);
  }

  public Event getById(Long eventId) {
    log.info(String.format(Constants.F_GETTING_WITH_ID + "%n", "event", eventId));
    return eventRepository.findById(eventId).orElse(new Event("Oopsies"));
  }
}
