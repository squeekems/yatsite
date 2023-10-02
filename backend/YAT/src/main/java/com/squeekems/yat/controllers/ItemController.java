package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Item;
import com.squeekems.yat.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

  @Autowired
  ItemService itemService;

  @GetMapping
  public List<Item> getItems() {
    return itemService.findAll();
  }
}
