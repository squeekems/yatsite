package com.squeekems.yat.services;

import com.squeekems.yat.entities.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

  @Autowired
  ItemService itemService;

  @Test
  void findAll() {
    // Given: Database has rows on events table

    // When:
    List<Item> actual = itemService.findAll();
    System.out.println(actual);

    // Then:
    assertNotNull(actual);
  }

  @Test
  void save() {
    // Given:
    Item item = new Item();

    // When:
    Item actual = itemService.save(item);

    // Then:
    assertNotNull(actual);
  }

//  @Test
//  void getById() {
//    // Given:
//    Long id = 1L;
//
//    // When:
//    Item actual = itemService.getById(id);
//
//    // Then:
//    assertNotNull(actual);
//  }
}