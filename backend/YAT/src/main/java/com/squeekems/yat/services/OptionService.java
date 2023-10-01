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
    log.info(String.format(Constants.fFindingAll + "%n", "options"));
    return optionRepository.findAll();
  }

  public void save(Option option) {
    log.info(String.format(Constants.fSaving + "%n", option));
    optionRepository.save(option);
  }

  public void delete(Option option) {
    log.info(String.format(Constants.fDeleting + "%n", option));
    optionRepository.delete(option);
  }

  public Option getOptionById(Long optionId) {
    log.info(String.format(Constants.fGettingWithId + "%n", "option", optionId));
    return optionRepository.findById(optionId).orElseThrow();
  }
}
