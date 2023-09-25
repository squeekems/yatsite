package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Option;
import com.squeekems.yat.entities.Player;
import com.squeekems.yat.services.EventService;
import com.squeekems.yat.services.OptionService;
import com.squeekems.yat.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

@RestController
@RequestMapping("/events")
public class EventController {

  @Autowired
  EventService eventService;

  @Autowired
  OptionService optionService;

  @GetMapping
  public List<Event> getEvents() {
    return eventService.findAll();
  }

  @GetMapping("/results")
  public List<Event> getResults() {
    List<Event> results = new ArrayList<>();

    for (Event event: eventService.findAll()) {
      if (!event.isCard()) {
        results.add(event);
      }
    }

    System.out.println("Number of results: " + results.size());
    return results;
  }

  @GetMapping("/dsCards")
  public List<Event> getDSCards() {
    List<Event> cards = new ArrayList<>();

    for (Event event: eventService.findAll()) {
      if (!(event.getDsPrompt() == null)) {
        cards.add(event);
      }
    }

    System.out.println("Number of dragonscript cards: " + cards.size());
    return cards;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/get")
  public Event getEvent(@RequestParam Long id){
    return eventService.getById(id);
  }

  @RequestMapping("/addOption")
  public void addOption(@RequestParam("id") Long id,
                        @RequestParam("optionId") Long optionId) {
    Event event = eventService.getById(id);
    Option option = optionService.getOptionById(optionId);
    event.getOptions().add(option);
    eventService.save(event);
  }

  @RequestMapping("/post")
  public void postEvent(@RequestParam("prompt") String prompt) {
    Event event = new Event(prompt);
    eventService.save(event);
  }

  @RequestMapping("/removeOption")
  public void removeOption(@RequestParam("id") Long id,
                           @RequestParam("optionId") Long optionId) {
    Event event = eventService.getById(id);
    Option option = optionService.getOptionById(optionId);
    event.getOptions().remove(option);
    eventService.save(event);
  }

  @RequestMapping("/delete")
  public void deleteById(@RequestParam("id")Long id) {
    eventService.deleteById(id);
  }
}
