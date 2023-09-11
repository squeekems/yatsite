package com.squeekems.yat.controllers;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Option;
import com.squeekems.yat.services.EventService;
import com.squeekems.yat.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/events")
public class EventController {

  @Autowired
  EventService eventService;

  @Autowired
  OptionService optionService;

  @RequestMapping("/random")
  public Event getRandom() {
    List<Event> cards = getCards();

    return cards.get(new Random().nextInt(cards.size()));
  }

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

  @GetMapping("/cards")
  public List<Event> getCards() {
    List<Event> cards = new ArrayList<>();

    for (Event event: eventService.findAll()) {
      if (event.isCard()) {
        cards.add(event);
      }
    }

    System.out.println("Number of cards: " + cards.size());
    return cards;
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
    return eventService.getEventById(id);
  }

  @RequestMapping("/post")
  public void postResult(@RequestParam("prompt") String prompt,
                         @RequestParam("isCard") boolean isCard,
                         @RequestParam List<String> options,
                         @RequestParam List<String> results) {
    if (options.size() == results.size()) {

      List<Option> eventOptions = new ArrayList<>();
      List<Event> optionResults = new ArrayList<>();

      for (String result : results) {
        Event event = new Event(result);
        eventService.save(event);
        optionResults.add(event);
      }

      for (String label : options) {
        Option option = new Option(label, optionResults.get(options.indexOf(label)));
        eventOptions.add(option);
        optionService.save(option);
      }

      Event event = new Event(prompt, isCard, new HashSet<>(eventOptions));
      eventService.save(event);
    } else {
      System.out.println("The number of Options should be equivalent to the number of Results.");
    }

  }

//  @RequestMapping("/random")
//  public Event getRandom() {
//    Event rndEvent = eventService.getEventById(new Random().nextLong(eventService.findAll().size()));
//
//    while (!rndEvent.isCard()) {
//      rndEvent = eventService.getEventById(new Random().nextLong(eventService.findAll().size()));
//    }
//    return rndEvent;
//  }
}
