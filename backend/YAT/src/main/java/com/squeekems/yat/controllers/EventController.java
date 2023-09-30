package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.utilityEntities.Card;
import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Option;
import com.squeekems.yat.entities.utilityEntities.CardEvent;
import com.squeekems.yat.entities.utilityEntities.OptionResult;
import com.squeekems.yat.services.EventService;
import com.squeekems.yat.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

  @GetMapping("/card")
  public Object getCard(@RequestParam("id") Long id) {
    Event event = eventService.getById(id);
    CardEvent cardEvent = new CardEvent();
    cardEvent.setEventId(event.getEventId());
    cardEvent.setPrompt(event.getPrompt());
    cardEvent.setDsPrompt(event.getDsPrompt());
    if (event != null && event.isCard()) {
      Card card = new Card();
      List<OptionResult> resultList = new ArrayList<>();
      for (Option option : event.getOptions()) {
        OptionResult optionResult = new OptionResult();

        optionResult.setOptionId(option.getOptionId());
        optionResult.setOptionLabel(option.getLabel());
        optionResult.setResultId(option.getResultId());
        optionResult.setResultPrompt(eventService.getById(option.getResultId()).getPrompt());
        resultList.add(optionResult);
      }
      card.setCardEvent(cardEvent);
      card.setOptionResult(resultList);
      return card;
    } else {
      return "No card found for eventId '" + id + "'";
    }
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
  public Event getEvent(@RequestParam Long id) {
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
