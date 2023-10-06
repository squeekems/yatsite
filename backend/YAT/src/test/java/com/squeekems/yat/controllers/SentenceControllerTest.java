package com.squeekems.yat.controllers;

import com.squeekems.yat.services.SentenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.squeekems.yat.util.Constants.FLAG_SKIP;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SentenceControllerTest {

  @Mock
  SentenceService sentenceService;
  @InjectMocks
  SentenceController sentenceController;
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(sentenceController).build();
  }

  @Test
  void getSentences() throws Exception {
    // Given:

    // When:
    ResultActions result = mockMvc.perform(get("/sentences"));

    // Then:
    verify(sentenceService).findAll();
    result.andExpect(status().isOk());
  }

  @Test
  void getAllByFlag() throws Exception {
    // Given:

    // When:
    ResultActions result = mockMvc.perform(
        get("/sentences/flag").param("flag", FLAG_SKIP)
    );

    // Then:
    verify(sentenceService).findAllByFlag(any(String.class));
    result.andExpect(status().isOk());
  }
}