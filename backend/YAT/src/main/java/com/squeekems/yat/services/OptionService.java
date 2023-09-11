package com.squeekems.yat.services;

import com.squeekems.yat.entities.Option;
import com.squeekems.yat.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {

  @Autowired
  private OptionRepository optionRepository;

  public List<Option> findAll() {
    System.out.println("Finding all options");
    return optionRepository.findAll();
  }

  public void save(Option option) {
    System.out.println("Saving \"" + option + "\"");
    optionRepository.save(option);
  }

  public void delete(Option option) {
    System.out.println("Deleting \"" + option + "\"");
    optionRepository.delete(option);
  }

  public Option getOptionById(Long optionId) {
    System.out.println("Getting option with id: " + optionId);
    return optionRepository.findById(optionId).orElseThrow();
  }
}
