package com.squeekems.yat.services;

import com.squeekems.yat.entities.Option;
import com.squeekems.yat.repositories.OptionRepository;
import com.squeekems.yat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {
  Logger log = LoggerFactory.getLogger(OptionService.class);

  @Autowired
  private OptionRepository optionRepository;

  public List<Option> findAll() {
    List<Option> options = optionRepository.findAll();
    log.info(String.format(Constants.F_FINDING_ALL + "%n", "options", options.size()));
    return options;
  }

  public void save(Option option) {
    log.info(String.format(Constants.F_SAVING + "%n", option));
    optionRepository.save(option);
  }

  public void delete(Option option) {
    log.info(String.format(Constants.F_DELETING + "%n", option));
    optionRepository.delete(option);
  }

  public Option getById(Long optionId) {
    log.info(String.format(Constants.F_GETTING_WITH_ID + "%n", "option", optionId));
    return optionRepository.findById(optionId).orElseThrow();
  }
}
