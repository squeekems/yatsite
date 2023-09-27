package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Sentence;
import com.squeekems.yat.services.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sentences")
public class SentenceController {
  @Autowired
  SentenceService sentenceService;

  @GetMapping
  public List<Sentence> getSentences() {
    return sentenceService.findAll();
  }

  @GetMapping("/flag")
  public List<Sentence> getAllByFlag(@RequestParam("flag") String flag) {
    return sentenceService.findAllByFlag(flag);
  }
}
