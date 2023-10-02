package com.squeekems.yat.util;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Player;
import com.squeekems.yat.entities.utilityEntities.flagHandlers.FlagHandler;
import com.squeekems.yat.entities.utilityEntities.flagHandlers.RollFlagHandler;
import com.squeekems.yat.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.squeekems.yat.util.Constants.*;

@Component
public class EventHandler {

  @Autowired
  PlayerService playerService;

  public Event processEvent(Event event, Player badLuckBrandon) {
    Event result = new Event();
    List<String> flagList = event.getFlagList();
    for (String eventFlag : flagList) {
      switch (eventFlag) {
        case FLAG_SKIP -> playerService.addSkipTo(badLuckBrandon);
        case FLAG_ROLL -> rollDice(1, 20);
        case FLAG_INVENTORY -> System.out.println("youGoodHolmes()");
        case FLAG_SAVING_THROW -> playerService.rollSavingThrowFor(badLuckBrandon);
        case FLAG_COMBAT -> {}
        default -> {}
      }
    }

    return result;
  }

  private void fuckAround() {

  }

//    if (flag.getKey == rollDie && flag.getValue == true) {
//      rolldie(event.dieMin, event.dieMax);
//    }
//    if else (inventoryCheck == true) {
//    if(checkInventory(List < inventoryId >)) {
//      youGoodHolmes();
//    } else {
//      delete badLuckBrandon;
//    }
//  }
//    if else(slapInFace == True) {
//    getRektNerd();
//  } else {
//    continue;
//  }
//  }
}
