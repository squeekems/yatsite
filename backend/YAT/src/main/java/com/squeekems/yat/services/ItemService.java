package com.squeekems.yat.services;

import com.squeekems.yat.entities.Item;
import com.squeekems.yat.repositories.ItemRepository;
import com.squeekems.yat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
  Logger log = LoggerFactory.getLogger(ItemService.class);

  @Autowired
  private ItemRepository itemRepository;

  public List<Item> findAll() {
    log.info(String.format(Constants.fFindingAll + "%n", "items"));
    return itemRepository.findAll();
  }

  public Item save(Item item) {
    log.info(String.format(Constants.fSaving + "%n", item));
    return itemRepository.save(item);
  }

  public Item getById(Long itemId) {
    log.info(String.format(Constants.fGettingWithId + "%n", "event", itemId));
    return itemRepository.findById(itemId).orElseThrow();
  }
}
