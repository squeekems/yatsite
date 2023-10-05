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
  void getIntroWhenBadRoomIdIsSelected() {
    // Given:
    playersPickedRooms(309);

    // When:
    String actual = introBuilder.getIntro();
    System.out.println(actual);

    // Then:
    // Need to actually handle this mistake in a testable way.
  }

  @Test
  void getIntroWhenBarAndBadRoomIdIsSelected() {
    // Given:
    playersPickedRooms(310, 314);

    // When:
    String actual = introBuilder.getIntro();

    // Then:
    // Need to actually handle this mistake in a testable way.
  }

  @Test
  void getIntroWhenDiningAndBadRoomIdIsSelected() {
    // Given:
    playersPickedRooms(311, 314);

    // When:
    String actual = introBuilder.getIntro();

    // Then:
    // Need to actually handle this mistake in a testable way.
  }

  @Test
  void getIntroWhenLibraryAndBadRoomIdIsSelected() {
    // Given:
    playersPickedRooms(312, 314);

    // When:
    String actual = introBuilder.getIntro();

    // Then:
    // Need to actually handle this mistake in a testable way.
  }

  @Test
  void getIntroWhenBarIsSelected() {
    // Given:
    playersPickedRooms(310);
    String expected = "introTest barSentenceTest barToConcludingTransitionTest " +
        "concludingSentenceTest ";

    // When:
    String actual = introBuilder.getIntro();

    // Then:
    assertEquals(expected, actual);
  }

  @Test
  void getIntroWhenBarAndLibraryIsSelected() {
    // Given:
    playersPickedRooms(310, 312);
    String expected = "introTest barSentenceTest barToLibraryTransitionTest librarySentenceTest " +
        "libraryToConcludingTransitionTest concludingSentenceTest ";

    // When:
    String actual = introBuilder.getIntro();

    // Then:
    assertEquals(expected, actual);
  }

  @Test
  void getIntroWhenBarAndPersonalIsSelected() {
    // Given:
    playersPickedRooms(310, 313);
    String expected = "introTest barSentenceTest barToPersonalTransitionTest " +
        "personalSentenceTest personalToConcludingTransitionTest concludingSentenceTest ";

    // When:
    String actual = introBuilder.getIntro();

    // Then:
    assertEquals(expected, actual);
  }

  @Test
  void getIntroWhenDiningIsSelected() {
    // Given:
    playersPickedRooms(311);
    String expected = "introTest diningSentenceTest diningToConcludingTransitionTest " +
        "concludingSentenceTest ";

    // When:
    String actual = introBuilder.getIntro();

    // Then:
    assertEquals(expected, actual);
  }

  @Test
  void getIntroWhenDiningAndPersonalIsSelected() {
    // Given:
    playersPickedRooms(311, 313);
    String expected = "introTest diningSentenceTest diningToPersonalTransitionTest " +
        "personalSentenceTest personalToConcludingTransitionTest concludingSentenceTest ";

    // When:
    String actual = introBuilder.getIntro();

    // Then:
    assertEquals(expected, actual);
  }

  @Test
  void getIntroWhenLibraryIsSelected() {
    // Given:
    playersPickedRooms(312);
    String expected = "introTest librarySentenceTest libraryToConcludingTransitionTest " +
        "concludingSentenceTest ";

    // When:
    String actual = introBuilder.getIntro();

    // Then:
    assertEquals(expected, actual);
  }

  @Test
  void getIntroWhenPersonalIsSelected() {
    // Given:
    playersPickedRooms(313);
    String expected = "introTest personalSentenceTest personalToConcludingTransitionTest " +
        "concludingSentenceTest ";

    // When:
    String actual = introBuilder.getIntro();
//    System.out.println(actual);

    // Then:
    assertEquals(expected, actual);
  }

  @Test
  void getIntroWhenBarDiningLibraryAndPersonalIsSelected() {
    // Given:
    playersPickedRooms(310, 311, 312, 313);
    String expected = "introTest barSentenceTest barToDiningTransitionTest diningSentenceTest " +
        "diningToLibraryTransitionTest librarySentenceTest libraryToPersonalTransitionTest " +
        "personalSentenceTest personalToConcludingTransitionTest concludingSentenceTest ";

    // When:
    String actual = introBuilder.getIntro();

    // Then:
    assertEquals(expected, actual);
  }

  private void playersPickedRooms(int... roomIds) {
    when(playerService.findAll()).then(new Answer<List<Player>>() {
      @Override
      public List<Player> answer(InvocationOnMock invocationOnMock) throws Throwable {
        List<Player> players = new ArrayList<>();
        for (int roomId: roomIds) {
          Player player = new Player();
          player.setRoom(roomId);
          players.add(player);
        }
        return players;
      }
    });
  }
}