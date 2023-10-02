package com.squeekems.yat.services;

import com.squeekems.yat.entities.Item;
import com.squeekems.yat.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ItemServiceTest {
  @Mock
  ItemRepository itemRepository;
  @InjectMocks
  ItemService itemService;

  @Test
  void findAll() {
    // Given:
    when(itemRepository.findAll()).then(new Answer<List<Item>>() {
      @Override
      public List<Item> answer(InvocationOnMock invocationOnMock) throws Throwable {
        return new ArrayList<Item>();
      }
    });

    // When:
    List<Item> actual = itemService.findAll();

    // Then:
    assertNotNull(actual);
  }

  @Test
  void save() {
    // Given:
    Item item = new Item();
    when(itemRepository.save(any(Item.class))).then(new Answer<Item>() {
      Long sequence = 1L;
      @Override
      public Item answer(InvocationOnMock invocationOnMock) throws Throwable {
        Item newItem = (Item) invocationOnMock.getArgument(0);
        newItem.setItemId(sequence++);
        return newItem;
      }
    });

    // When:
    Item actual = itemService.save(item);

    // Then:
    verify(itemRepository).save(item);
    assertNotNull(actual);
  }

  @Test
  void getById() {
    // Given:
    Long id = 1L;
    when(itemRepository.findById(any(Long.class))).then(new Answer<Optional<Item>>() {
      @Override
      public Optional<Item> answer(InvocationOnMock invocationOnMock) throws Throwable {
        Long itemId = (Long) invocationOnMock.getArgument(0);
        Item item = new Item();
        item.setItemId(itemId);
        return Optional.of(item);
      }
    });

    // When:
    Item actual = itemService.getById(id);

    // Then:
    verify(itemRepository).findById(id);
    assertNotNull(actual);
  }
}