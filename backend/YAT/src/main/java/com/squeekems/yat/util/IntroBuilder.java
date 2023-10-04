package com.squeekems.yat.util;

import com.squeekems.yat.entities.Player;
import com.squeekems.yat.entities.Sentence;
import com.squeekems.yat.services.PlayerService;
import com.squeekems.yat.services.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.squeekems.yat.util.Constants.roomFlags.*;

@Component
public class IntroBuilder {
  @Autowired
  PlayerService playerService;
  @Autowired
  SentenceService sentenceService;

  IntroBuilder() {
  }

  private void sayOops() {
    System.out.println("Oopsies");
  }

  public String getIntro() {
    List<Player> players = playerService.findAll();
    Set<Integer> setRooms = new HashSet<>();
    StringBuilder s = new StringBuilder();
    for (Player player : players) {
      setRooms.add(player.getRoom());
    }
    List<Integer> rooms = new ArrayList<>(setRooms);
    System.out.println("rooms=" + rooms);

    flagFinder(s, intro.name());

    for (int i = 0; i < rooms.size(); i++) {
      System.out.println("i='" + i + "'");
      switch (rooms.get(i)) {
        case 310 -> {
          flagFinder(s, barSentence.name());
          if (i + 1 != rooms.size()) {
            switch (rooms.get(i + 1)) {
              case 311 -> flagFinder(s, barToDiningTransition.name());
              case 312 -> flagFinder(s, barToLibraryTransition.name());
              case 313 -> flagFinder(s, barToPersonalTransition.name());
              default -> sayOops();
            }
          }
          if (i == rooms.size() - 1) {
            flagFinder(s, barToConcludingTransition.name());
          }
        }
        case 311 -> {
          flagFinder(s, diningSentence.name());
          if (i + 1 != rooms.size()) {
            switch (rooms.get(i + 1)) {
              case 312 -> flagFinder(s, diningToLibraryTransition.name());
              case 313 -> flagFinder(s, diningToPersonalTransition.name());
              default -> sayOops();
            }
          }
          if (i == rooms.size() - 1) {
            flagFinder(s, diningToConcludingTransition.name());
          }
        }
        case 312 -> {
          flagFinder(s, librarySentence.name());
          if (i + 1 != rooms.size()) {
            if (rooms.get(i + 1) == 313) {
              flagFinder(s, libraryToPersonalTransition.name());
            }
          }
          if (i == rooms.size() - 1) {
            flagFinder(s, libraryToConcludingTransition.name());
          }
        }
        case 313 -> {
          flagFinder(s, personalSentence.name());
          flagFinder(s, personalToConcludingTransition.name());
        }
        default -> sayOops();
      }
    }
    flagFinder(s, concludingSentence.name());
    return s.toString();
  }

  private static String getRFrom(List<Sentence> sentences) {
    return sentences.get(new Random().nextInt(sentences.size())).getSentence();
  }

  private void flagFinder(StringBuilder s, String flag) {
    s.append(getRFrom(sentenceService.findAllByFlag(flag))).append(" ");
  }
}
