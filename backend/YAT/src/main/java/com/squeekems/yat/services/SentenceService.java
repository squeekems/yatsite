package com.squeekems.yat.services;

import com.squeekems.yat.entities.Sentence;
import com.squeekems.yat.repositories.SentenceRepository;
import com.squeekems.yat.util.Constants;
import com.squeekems.yat.util.SentenceFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentenceService {
  @Autowired
  private SentenceRepository sentenceRepository;

  public List<Sentence> findAll() {
    System.out.printf(Constants.fFindingAll + "%n", "sentences");
    return sentenceRepository.findAll();
  }

  public List<SentenceFlag> findUniqueFlags() {
    return sentenceRepository.findUniqueFlags();
  }

  public List<Sentence> findAllByFlag(String flag) {
    System.out.printf(Constants.fFindingAll + "%n", flag + " sentences");
    return sentenceRepository.findAllByFlag(flag);
  }

  public void save(Sentence sentence) {
    System.out.printf(Constants.fSaving + "%n", sentence);
    sentenceRepository.save(sentence);
  }

  public void delete(Sentence sentence) {
    System.out.printf(Constants.fDeleting + "%n", sentence);
    sentenceRepository.delete(sentence);
  }

  public Sentence getById(Long id) {
    System.out.printf(Constants.fGettingWithId + "%n", "sentence", id);
    return sentenceRepository.findById(id).orElseThrow();
  }
}
