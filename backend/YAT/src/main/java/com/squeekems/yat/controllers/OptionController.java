package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Option;
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

  @GetMapping
  public List<Option> getOptions() {
    return optionService.findAll();
  }

  @GetMapping("/option")
  public Option getOption(@RequestParam Long id){
    return optionService.getById(id);
  }
}
