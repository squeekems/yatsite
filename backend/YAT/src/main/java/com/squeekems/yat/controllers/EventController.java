package com.squeekems.yat.controllers;

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

  @GetMapping
  public List<Event> getEvents() {
    return eventService.findAll();
  }

  @RequestMapping("/random")
  public Event getRandom() {
    List<Event> cards = getCards();

    return cards.get(new Random().nextInt(cards.size()));
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

  @RequestMapping("/addOptionString")
  public void addOptionString(@RequestParam("id") Long id,
                        @RequestParam("option") String option) {
    Event event = eventService.getById(id);
    Option newOption = new Option(option);
    event.getOptions().add(newOption);
    optionService.save(newOption);
    eventService.save(event);
  }

  @RequestMapping("/addOptionStrings")
  public void addOptionStrings(@RequestParam("id") Long id,
                        @RequestParam("option") String option,
                        @RequestParam("result") String result) {
    Event event = eventService.getById(id);
    Option newOption = new Option(option, result);
    event.getOptions().add(newOption);
    eventService.save(newOption.getResult());
    optionService.save(newOption);
    eventService.save(event);
  }

  @RequestMapping("/post")
  public void postEvent(@RequestParam("prompt") String prompt) {
    Event event = new Event(prompt);
    eventService.save(event);
  }

  @RequestMapping("/postFull")
  public void postEventFull(@RequestParam("prompt") String prompt,
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
      System.out.println("The number of Options(" + options.size() +
          ") should be equivalent to the number of Results(" + results.size() + ").");
    }

  }

  @RequestMapping("/removeOption")
  public void removeOption(@RequestParam("id") Long id,
                           @RequestParam("optionId") Long optionId) {
    Event event = eventService.getById(id);
    Option option = optionService.getOptionById(optionId);
    event.getOptions().remove(option);
    eventService.save(event);
  }

//  @RequestMapping("/random")
//  public Event getRandom() {
//    Event rndEvent = eventService.getById(new Random().nextLong(eventService.findAll().size()));
//
//    while (!rndEvent.isCard()) {
//      rndEvent = eventService.getById(new Random().nextLong(eventService.findAll().size()));
//    }
//    return rndEvent;
//  }
}
