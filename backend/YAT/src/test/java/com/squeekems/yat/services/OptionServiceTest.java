package com.squeekems.yat.services;

import com.squeekems.yat.entities.Option;
import com.squeekems.yat.repositories.OptionRepository;
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
class OptionServiceTest {

  @Mock
  OptionRepository optionRepository;
  @InjectMocks
  OptionService optionService;

  @Test
  void findAll() {
    // Given:
    when(optionRepository.findAll()).then(new Answer<List<Option>>() {
      @Override
      public List<Option> answer(InvocationOnMock invocationOnMock) throws Throwable {
        return new ArrayList<Option>();
      }
    });

    // When:
    List<Option> actual = optionService.findAll();

    // Then:
    assertNotNull(actual);
  }

  @Test
  void save() {
    // Given:
    Option option = new Option();
    when(optionRepository.save(any(Option.class))).then(new Answer<Option>() {
      Long sequence = 1L;
      @Override
      public Option answer(InvocationOnMock invocationOnMock) throws Throwable {
        Option newOption = (Option) invocationOnMock.getArgument(0);
        newOption.setOptionId(sequence++);
        return newOption;
      }
    });

    // When:
    Option actual = optionService.save(option);

    // Then:
    verify(optionRepository).save(option);
    assertNotNull(actual);
  }

  @Test
  void delete() {
    // Given:
    Option option = new Option();

    // When:
    optionService.delete(option);

    // Then:
    verify(optionRepository).delete(option);
  }

  @Test
  void getById() {
    // Given:
    Long id = 1L;
    when(optionRepository.findById(any(Long.class))).then(new Answer<Optional<Option>>() {
      @Override
      public Optional<Option> answer(InvocationOnMock invocationOnMock) throws Throwable {
        Long optionId = (Long) invocationOnMock.getArgument(0);
        Option option = new Option();
        option.setOptionId(optionId);
        return Optional.of(option);
      }
    });

    // When:
    Option actual = optionService.getById(id);

    // Then:
    verify(optionRepository).findById(id);
    assertNotNull(actual);
  }
}