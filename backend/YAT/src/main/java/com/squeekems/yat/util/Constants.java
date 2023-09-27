package com.squeekems.yat.util;

import java.util.List;
import java.util.Random;

public class Constants {
  public static final Long event1Id = 309L;
  public static final String fSaving = "Saving \"%s\"";
  public static final String fDeleting = "Deleting \"%s\"";
  public static final String fGettingWithId = "Getting %s with id: %s";
  public static final String fFindingAll = "Finding all %s";
  public static final String youAreInATavern =
      "Where would we find you in the tavern at this time? Consider your current setting.";
  public static final String whatIsYourName  = "What is your name traveler?";
  public static final String fWelcomeToTavern = "Welcome to the tavern, %s.";
  public static final String fOption = "%d) %s";
  public static final String optionContinue = "Continue...";
  public static final String skipQueue = "Skip your next turn.";
  public static final String moveQueue = "move your progress tracker ";
  public static final String moveUpQueue = moveQueue + "up by ";
  public static final String moveDownQueue = moveQueue + "down by ";
  public static final String fMoveSingular = "%s moved %s by %d space from %d to %d.";
  public static final String fMovePlural = "%s moved %s by %d spaces from %d to %d.";

//  public static String getIntro(List<Integer> rooms) {
//    StringBuilder s = new StringBuilder();
//
//    s.append(getRandomFrom(intro)).append(" ");
//
//    s.append(getRandomFrom(roomIntro[rooms.get(0)])).append(" ");
//
//    for (int i = 1; i < rooms.size(); i++) {
//      switch (rooms.get(i - 1)) {
//        case 0 -> {
//          switch (rooms.get(i)) {
//            case 1 -> s.append(getRandomFrom(introRoomTransition[0])).append(" ");
//            case 2 -> s.append(getRandomFrom(introRoomTransition[1])).append(" ");
//            case 3 -> s.append(getRandomFrom(introRoomTransition[2])).append(" ");
//            default -> s.append(getRandomFrom(introRoomTransition[3])).append(" ");
//          }
//        }
//        case 1 -> {
//          switch (rooms.get(i)) {
//            case 2 -> s.append(getRandomFrom(introRoomTransition[4])).append(" ");
//            case 3 -> s.append(getRandomFrom(introRoomTransition[5])).append(" ");
//            default -> s.append(getRandomFrom(introRoomTransition[6])).append(" ");
//          }
//        }
//        case 2 -> {
//          if (rooms.get(i) == 3) {
//            s.append(getRandomFrom(introRoomTransition[7])).append(" ");
//          } else {
//            s.append(getRandomFrom(introRoomTransition[8])).append(" ");
//          }
//        }
//        default -> s.append(getRandomFrom(introRoomTransition[9])).append(" ");
//      }
//
//      if ( i != rooms.size() - 1) {
//        s.append(getRandomFrom(roomIntro[rooms.get(i)])).append(" ");
//      }
//    }
//
//    s.append(getRandomFrom(introConclusion));
//
//    return s.toString();
//  }

  private static String getRandomFrom(String[] s) {
    return s[new Random().nextInt(s.length)];
  }
}
