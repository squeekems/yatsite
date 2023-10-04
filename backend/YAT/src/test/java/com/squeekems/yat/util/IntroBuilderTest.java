package com.squeekems.yat.util;

import com.squeekems.yat.entities.Player;
import com.squeekems.yat.entities.Sentence;
import com.squeekems.yat.services.PlayerService;
import com.squeekems.yat.services.SentenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class IntroBuilderTest {

  @Mock
  PlayerService playerService;
  @Mock
  SentenceService sentenceService;
  @InjectMocks
  IntroBuilder introBuilder;

  @BeforeEach
  void setUp() {
    Long counter = 0L;
    for (Constants.roomFlags flag : Constants.roomFlags.values()) {
      setSentenceService(counter, flag.name(), flag.name() + "Test");
      counter++;
    }
  }

  private void setSentenceService(Long counter, String flag, String sentence) {
    when(sentenceService.findAllByFlag(flag)).then(new Answer<List<Sentence>>() {
      @Override
      public List<Sentence> answer(InvocationOnMock invocationOnMock) throws Throwable {
        List<Sentence> sentences = new ArrayList<>();
        Sentence newSentence = new Sentence();
        newSentence.setId(counter);
        newSentence.setSentence(sentence);
        newSentence.setFlag(flag);
        sentences.add(newSentence);
        return sentences;
      }
    });
  }

  @Test
  void getIntroWhenBarIsSelected() {
    // Given:
    when(playerService.findAll()).then(new Answer<List<Player>>() {
      @Override
      public List<Player> answer(InvocationOnMock invocationOnMock) throws Throwable {
        List<Player> players = new ArrayList<>();
        Player player = new Player();
        player.setRoom(310);
        players.add(player);
        return players;
      }
    });
    String expected = "introTest barSentenceTest barToConcludingTransitionTest " +
        "concludingSentenceTest ";

    // When:
    String actual = introBuilder.getIntro();

    // Then:
    assertEquals(expected, actual);
  }
}