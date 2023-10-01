package com.squeekems.yat.services;

import com.squeekems.yat.entities.Sentence;
import com.squeekems.yat.repositories.SentenceRepository;
import com.squeekems.yat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentenceService {
  Logger log = LoggerFactory.getLogger(SentenceService.class);
  
  @Autowired
  private SentenceRepository sentenceRepository;

  public List<Sentence> findAll() {
    log.info(String.format(Constants.fFindingAll + "%n", "sentences"));
    return sentenceRepository.findAll();
  }

  public List<Sentence> findAllByFlag(String flag) {
    log.info(String.format(Constants.fFindingAll + "%n", flag + " sentences"));
    return sentenceRepository.findAllByFlag(flag);
  }

  public void save(Sentence sentence) {
    log.info(String.format(Constants.fSaving + "%n", sentence));
    sentenceRepository.save(sentence);
  }

  public void delete(Sentence sentence) {
    log.info(String.format(Constants.fDeleting + "%n", sentence));
    sentenceRepository.delete(sentence);
  }

  public Sentence getById(Long id) {
    log.info(String.format(Constants.fGettingWithId + "%n", "sentence", id));
    return sentenceRepository.findById(id).orElseThrow();
  }
}
