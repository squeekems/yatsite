package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Option;
import com.squeekems.yat.services.EventService;
import com.squeekems.yat.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/options")
public class OptionController {

  @Autowired
  OptionService optionService;

  @Autowired
  EventService eventService;

  @GetMapping
  public List<Option> getOptions() {
    return optionService.findAll();
  }

  @RequestMapping("/post")
  public void postOption(@RequestParam("label") String label) {
    Option option = new Option(label);
    optionService.save(option);
  }

  @RequestMapping("/post")
  public void postOption(@RequestParam("label") String label, @RequestParam("result") String result) {
    Option option = new Option(label, result);
    eventService.save(option.getResult());
    optionService.save(option);
  }

  @RequestMapping("/setResult")
  public void setResult(@RequestParam("id") Long id, @RequestParam("resultId") Long resultId) {
    Option option = optionService.getOptionById(id);
    Event result = eventService.getEventById(resultId);
    option.setResult(result);
    optionService.save(option);
  }
}
