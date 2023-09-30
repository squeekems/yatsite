package com.squeekems.yat.services;

import com.squeekems.yat.entities.Item;
import com.squeekems.yat.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;

  public List<Item> findAll() {
    return itemRepository.findAll();
  }

  public Item save(Item item) {
    return itemRepository.save(item);
  }

  public Item getById(Long itemId) {
    return itemRepository.findById(itemId).orElseThrow();
  }
}
