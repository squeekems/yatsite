package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Sentence;
import com.squeekems.yat.services.SentenceService;
import com.squeekems.yat.util.IntroBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/sentences")
public class SentenceController {
  @Autowired
  SentenceService sentenceService;
  @Autowired
  IntroBuilder introBuilder;

  @GetMapping
  public List<Sentence> getSentences() {
    return sentenceService.findAll();
  }

  @GetMapping("/flag")
  public List<Sentence> getAllByFlag(@RequestParam("flag") String flag) {
    return sentenceService.findAllByFlag(flag);
  }

  @RequestMapping("/intro")
  public String getIntro() {
    return introBuilder.getIntro();
  }
}
