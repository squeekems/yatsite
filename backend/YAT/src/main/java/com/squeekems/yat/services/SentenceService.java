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
    List<Sentence> sentences = sentenceRepository.findAll();
    log.info(String.format(Constants.F_FINDING_ALL, "sentences", sentences.size()));
    return sentences;
  }

  public List<Sentence> findAllByFlag(String flag) {
    List<Sentence> sentences = sentenceRepository.findAllByFlag(flag);
    log.info(String.format(
        Constants.F_FINDING_ALL, flag + " sentences by '" + flag + "'", sentences.size()
    ));
    return sentences;
  }

  public Sentence save(Sentence sentence) {
    log.info(String.format(Constants.F_SAVING, sentence));
    return sentenceRepository.save(sentence);
  }

  public void delete(Sentence sentence) {
    log.info(String.format(Constants.F_DELETING, sentence));
    sentenceRepository.delete(sentence);
  }

  public Sentence getById(Long id) {
    log.info(String.format(Constants.F_GETTING_WITH_ID, "sentence", id));
    return sentenceRepository.findById(id).orElseThrow();
  }
}
