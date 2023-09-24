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
  public static final String[] intro = {
      "You are there, in the room of your choice, relaxing."
  };
  public static final String[][] roomIntro = {
      {
        "There is the usual chatter of patrons enjoying conversation at the bar.",
          "A drunken laugh is heard over the roar of the tight crowd in the bar."
      },
      {
        "Glasses clang from the kitchen for those enjoying a meal in the dining area.",
          "Everyone is satisfied with their plate in the dining area."
      },
      {
        "Those in the library hear only the shuffling of pages turning.",
          "The library is quiet. The sound of a page turning is followed by a brief chuckle.",
          "Breathing in the library gives its patrons a familiar feeling."
      },
      {
        "Not a peep can be heard from the private rooms in the tavern.",
          "Weary travelers rest in cozy rooms."
      }
  };
  public static final String[] barToDiningTransition = {
      "A wooden platter, full of tasty drinks, is carried in one hand."
  };
  public static final String[] barToLibraryTransition = {
      "Despite the loud roar, the library is a pleasant place."
  };
  public static final String[] barToRoomTransition = {
      "The less social patrons to this tavern enjoy the stay too."
  };
  public static final String[] barToConclusionTransition = {
      "This awesome atmosphere is destined to be interrupted."
  };
  public static final String[] diningToLibraryTransition = {
      "The sizzling of food could not be heard from the library however."
  };
  public static final String[] diningToRoomTransition = {
      "And after enjoying their meal, patrons may retire deeper into the tavern."
  };
  public static final String[] diningToConclusionTransition = {
      "Hopefully they did not eat too much."
  };
  public static final String[] libraryToRoomTransition = {
      "The smell a good book in their hands puts their mind at ease."
  };
  public static final String[] libraryToConclusionTransition = {
      "The books shake on the shelves."
  };
  public static final String[] roomToConclusionTransition = {
      "One can only imagine a peaceful slumber is enjoyed by them. That slumber is to be interrupted, however.",
      "This tavern has some way of making even the maddest person feel at ease, but the madness cannot be held at bay forever."
  };
  public static final String[][] introRoomTransition = {
      barToDiningTransition,          // 0 bar
      barToLibraryTransition,         // 1 bar
      barToRoomTransition,            // 2 bar
      barToConclusionTransition,      // 3 bar
      diningToLibraryTransition,      // 4 dining
      diningToRoomTransition,         // 5 dining
      diningToConclusionTransition,   // 6 dining
      libraryToRoomTransition,        // 7 library
      libraryToConclusionTransition,  // 8 library
      roomToConclusionTransition      // 9 room
  };
  public static final String[] introConclusion = {
      "There is a sudden explosion heard from outside."
  };
  public static final String[] buildings = {
      "tavern", "farmstead", "butchery", "jail", "lumber mill", "fletcher", "gypsy cottage",
      "blacksmith", "barber", "greenhouse", "apothecary", "charm chamber", "barracks", "bakery",
      "library", "cobbler", "tailor", "bathhouse", "church", "town hall"
  };
  public static final String[] fortunes = {
      "She says, \"It will be heroic, but I see a fiery demise for you.\"",
      "She smiles and says, \"Your quick wits will get you out of a tough situation.\"",
      "She raises an inquisitive eyebrow and says, \"Your love of music will be an important part of your life?\"",
      "She smirks and says, \"You are in good hands this evening.\"",
      "She says, \"You are a person of another time.\"",
      "She breaks her glare for a moment before staring at you again. She nods, with concern on her face, saying, \"We first make our habits, and then our habits make us.\"",
      "Her face contorts into a sideways smirk. She says, \"Success is a journey, not a destination.\"",
      "Her eyes go wide for a moment before she looks down. She says, \"Protective measures will prevent costly disasters.\"",
      "She places her bony hand on your shoulder and says, \"Take the chance while you still have the choice.\"",
      "She looks up at the roaring dragon and says, \"Pick battles big enough to matter and small enough to win.\"",
      "She says, \"In order to take, one must first give.\"",
      "She says, \"Don’t confuse recklessness with confidence.\"",
      "She says, \"Depart not from the path which fate has you assigned.\"",
      "The woman looks you up and down. She says, \"Be careful or you could fall for some tricks today.\"",
      "She mournfully says, \"All the troubles you have will pass away very quickly.\""
  };

  public static String getIntroduction(List<Integer> rooms) {
    StringBuilder s = new StringBuilder();

    s.append(getRandomFrom(intro)).append(" ");

    s.append(getRandomFrom(roomIntro[rooms.get(0)])).append(" ");

    for (int i = 1; i < rooms.size(); i++) {
      switch (rooms.get(i - 1)) {
        case 0 -> {
          switch (rooms.get(i)) {
            case 1 -> s.append(getRandomFrom(introRoomTransition[0])).append(" ");
            case 2 -> s.append(getRandomFrom(introRoomTransition[1])).append(" ");
            case 3 -> s.append(getRandomFrom(introRoomTransition[2])).append(" ");
            default -> s.append(getRandomFrom(introRoomTransition[3])).append(" ");
          }
        }
        case 1 -> {
          switch (rooms.get(i)) {
            case 2 -> s.append(getRandomFrom(introRoomTransition[4])).append(" ");
            case 3 -> s.append(getRandomFrom(introRoomTransition[5])).append(" ");
            default -> s.append(getRandomFrom(introRoomTransition[6])).append(" ");
          }
        }
        case 2 -> {
          if (rooms.get(i) == 3) {
            s.append(getRandomFrom(introRoomTransition[7])).append(" ");
          } else {
            s.append(getRandomFrom(introRoomTransition[8])).append(" ");
          }
        }
        default -> s.append(getRandomFrom(introRoomTransition[9])).append(" ");
      }

      if ( i != rooms.size() - 1) {
        s.append(getRandomFrom(roomIntro[rooms.get(i)])).append(" ");
      }
    }

    s.append(getRandomFrom(introConclusion));

    return s.toString();
  }

  private static String getRandomFrom(String[] s) {
    return s[new Random().nextInt(s.length)];
  }
}
