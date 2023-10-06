package com.squeekems.yat.services;

import com.squeekems.yat.entities.Sentence;
import com.squeekems.yat.repositories.SentenceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.squeekems.yat.util.Constants.FLAG_SKIP;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SentenceServiceTest {

  @Mock
  SentenceRepository sentenceRepository;
  @InjectMocks
  SentenceService sentenceService;

  @Test
  void findAll() {
    // Given:
    when(sentenceRepository.findAll()).then(new Answer<List<Sentence>>() {
      @Override
      public List<Sentence> answer(InvocationOnMock invocationOnMock) throws Throwable {
        return new ArrayList<Sentence>();
      }
    });

    // When:
    List<Sentence> actual = sentenceService.findAll();

    // Then:
    assertNotNull(actual);
  }

  @Test
  void findAllByFlag() {
    // Given:
    String flag = FLAG_SKIP;
    when(sentenceRepository.findAllByFlag(any(String.class))).then(new Answer<List<Sentence>>() {
      @Override
      public List<Sentence> answer(InvocationOnMock invocationOnMock) throws Throwable {
        String newFlag = (String) invocationOnMock.getArgument(0);
        List<Sentence> sentences = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
          Sentence sentence = new Sentence();
          sentence.setFlag(newFlag);
          sentences.add(sentence);
        }
        return sentences;
      }
    });

    // When:
    List<Sentence> actual = sentenceService.findAllByFlag(flag);

    // Then:
    verify(sentenceRepository).findAllByFlag(flag);
    assertNotNull(actual);
  }

  @Test
  void save() {
    // Given:
    Sentence sentence = new Sentence();
    when(sentenceRepository.save(any(Sentence.class))).then(new Answer<Sentence>() {
      Long sequence = 1L;
      @Override
      public Sentence answer(InvocationOnMock invocationOnMock) throws Throwable {
        Sentence newSentence = (Sentence) invocationOnMock.getArgument(0);
        newSentence.setId(sequence++);
        return newSentence;
      }
    });

    // When:
    Sentence actual = sentenceService.save(sentence);

    // Then:
    verify(sentenceRepository).save(sentence);
    assertNotNull(actual);
  }

  @Test
  void delete() {
    // Given:
    Sentence sentence = new Sentence();

    // When:
    sentenceService.delete(sentence);

    // Then:
    verify(sentenceRepository).delete(sentence);
  }

  @Test
  void getById() {
    // Given:
    Long id = 1L;
    when(sentenceRepository.findById(any(Long.class))).then(new Answer<Optional<Sentence>>() {
      @Override
      public Optional<Sentence> answer(InvocationOnMock invocationOnMock) throws Throwable {
        Long sentenceId = (Long) invocationOnMock.getArgument(0);
        Sentence sentence = new Sentence();
        sentence.setId(sentenceId);
        return Optional.of(sentence);
      }
    });

    // When:
    Sentence actual = sentenceService.getById(id);

    // Then:
    verify(sentenceRepository).findById(id);
    assertNotNull(actual);
  }
}