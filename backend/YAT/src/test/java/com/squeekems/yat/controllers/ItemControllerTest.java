package com.squeekems.yat.controllers;

import com.squeekems.yat.services.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ItemControllerTest {

  @Mock
  ItemService itemService;
  @InjectMocks
  ItemController itemController;
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
  }

  @Test
  void getItems() throws Exception {
    // Given:

    // When:
    ResultActions result = mockMvc.perform(get("/items"));

    // Then:
    verify(itemService).findAll();
    result.andExpect(status().isOk());
  }
}