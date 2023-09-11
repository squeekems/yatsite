package com.squeekems.yat.util.oneshots;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Item;
import com.squeekems.yat.entities.Option;
import com.squeekems.yat.services.EventService;
import com.squeekems.yat.services.ItemService;
import com.squeekems.yat.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BeautifulBean {
  @Autowired
  EventService eventService;
  @Autowired
  ItemService itemService;
  @Autowired
  OptionService optionService;
  OneShot oneShot = new OneShot();

  public BeautifulBean() {
  }

  @RequestMapping("/bean")
  public void trigger() {
    rollFootage();
  }

  public void rollItemFootage() {
    for (Item item: oneShot.getItemList()) {
      itemService.save(item);
    }
  }

  public void rollFootage() {

    for (int i = 0; i < oneShot.getEventDeck().size(); i++) {
      EventCard eventCard = oneShot.getCardById(i);
      Set<Option> eventOptions = new HashSet<>();

      for (int y = 0; y < eventCard.strChoice.size(); y++) {
          Event eventResult;
          String stringResult = oneShot.getResults((eventCard)).get(y);

          if (Objects.equals(stringResult.substring(0, 1), "#")) {
            eventResult = saveNewResult(stringResult);
          } else {
            eventResult = new Event(stringResult, false, null);
            eventService.save(eventResult);
          }

          Option option = new Option(eventCard.strChoice.get(y), eventResult);

          optionService.save(option);
          eventOptions.add(option);

      }

      String prompt = oneShot.getCardById(i).strPrompt;
      String dsPrompt = null;

      if (prompt.contains("}")) {

        int indexOfBracket = prompt.indexOf("}");
        dsPrompt = prompt.substring(indexOfBracket + 1);
        prompt = prompt.substring(0, indexOfBracket);

      }

      Event event = new Event(prompt, dsPrompt, true, eventOptions);

      eventService.save(event);

    }

    rollItemFootage();
    dedupResults();
    dedupOptions();
  }

  public Event saveNewResult(String results) {

    String[] load = results.split("~");
    List<String> choices = new ArrayList<>();

    for (int i = 2; i < load.length; i++) {
      choices.add(load[i].substring(4, load[i].length()));
    }

    EventCard eventCard = new EventCard(load[0], load[1], choices);
    Set<Option> eventOptions = new HashSet<>();

    for (int i = 0; i < eventCard.strChoice.size(); i++) {

      Event result;
      String newResults = oneShot.getResults(eventCard).get(i);

      if (Objects.equals(newResults.substring(0, 1), "#")) {

        result = saveNewResult(newResults);

      } else {

        result = new Event(newResults, false, null);

      }

      eventService.save(result);

      Option option = new Option(eventCard.strChoice.get(i), result);

      optionService.save(option);

      eventOptions.add(option);

    }

    Event event = new Event(eventCard.strPrompt, false, eventOptions);

    eventService.save(event);

    return event;

  }

  public void dedupResults() {
    List<Option> options = optionService.findAll();
    Set<Event> results = new HashSet<>();

    for (Event event: eventService.findAll()) {
      if (!event.isCard()) {
        results.add(event);
      }
    }

    for (Event result: results) {
      for (Option option: options) {
        Event oldResult = option.getResult();
        if (result.equals(oldResult) && result != oldResult) {
          option.setResult(result);
          optionService.save(option);
          eventService.delete(oldResult);
        }
      }
    }
  }

  public void dedupOptions() {
    Set<Event> events = new HashSet<>(eventService.findAll());
    Set<Option> options = new HashSet<>(optionService.findAll());
    Set<Option> optionsToDelete = new HashSet<>();
    List<Event> eventsToEdit = new ArrayList<>();
    List<Option> optionsToRemove = new ArrayList<>();
    List<Option> optionsToAdd = new ArrayList<>();

    for (Option option: options) {
      for (Event event: events) {
        Set<Option> eventOptions = event.getOptions();
        if (eventOptions != null && eventOptions.size() > 0) {
          for (Option oldOption : eventOptions) {
            if (option.equals(oldOption) && option != oldOption) {
              eventsToEdit.add(event);
              optionsToRemove.add(oldOption);
              optionsToAdd.add(option);
              optionsToDelete.add(oldOption);
            }
          }
        }
      }
    }
    for (int i = 0; i < optionsToAdd.size(); i++) {
      Event event = eventsToEdit.get(i);
      Set<Option> eventOptions = event.getOptions();
      eventOptions.remove(optionsToRemove.get(i));
      eventOptions.add(optionsToAdd.get(i));
      eventService.save(event);
    }

    for (Option option: optionsToDelete) {
      optionService.delete(option);
    }
  }

}
