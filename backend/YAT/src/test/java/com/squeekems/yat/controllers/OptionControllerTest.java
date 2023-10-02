package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Option;
import com.squeekems.yat.services.OptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OptionControllerTest {
  @Mock
  OptionService optionService;
  @InjectMocks
  OptionController optionController;
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(optionController).build();
  }

  @Test
  void getOptions() throws Exception {
    // Given:

    // When:
    ResultActions result = mockMvc.perform(get("/options"));

    // Then:
    verify(optionService).findAll();
    result.andExpect(status().isOk());
  }

  @Test
  void getOption() throws Exception {
    // Given:
    Long id = 1L;
    when(optionService.getById(any(Long.class))).then(new Answer<Option>() {
      @Override
      public Option answer(InvocationOnMock invocationOnMock) throws Throwable {
        Long optionId = (Long) invocationOnMock.getArgument(0);
        Option option = new Option();
        option.setOptionId(optionId);
        return option;
      }
    });

    // When:
    ResultActions result = mockMvc.perform(
        get("/options/option").param("id", id.toString())
    );

    // Then:
    verify(optionService).getById(id);
    result.andExpect(status().isOk());
  }
}