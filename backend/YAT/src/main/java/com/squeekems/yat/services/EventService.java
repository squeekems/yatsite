package com.squeekems.yat.services;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.repositories.EventRepository;
import com.squeekems.yat.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
  @Autowired
  private EventRepository eventRepository;

  public List<Event> findAll() {
    System.out.printf(Constants.fFindingAll + "%n", "events");
    return eventRepository.findAll();
  }

  public Event save(Event event) {
    System.out.printf(Constants.fSaving + "%n", event);
    return eventRepository.save(event);
  }

  public void delete(Event event) {
    System.out.printf(Constants.fDeleting + "%n", event);
    eventRepository.delete(event);
  }

  public void deleteById(Long id) {
    System.out.printf(Constants.fDeleting + "%n", "event(" + id + ")");
    eventRepository.deleteById(id);
  }

  public Event getById(Long eventId) {
    System.out.printf(Constants.fGettingWithId + "%n", "event", eventId);
    return eventRepository.findById(eventId).orElse(new Event("Oopsies"));
  }

  public void dropTable() {
    eventRepository.dropTable();
  }
}
