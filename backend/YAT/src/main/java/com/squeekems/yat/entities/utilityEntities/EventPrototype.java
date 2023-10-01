package com.squeekems.yat.entities.utilityEntities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Option;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "events")
public class EventPrototype {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long eventId;
  @Lob
  @Column(length = 65535)
  private String prompt;
  @Lob
  @Column(length = 65535)
  private String dsPrompt;
  @Column
  private boolean isCard;
  @ElementCollection
  private Map<String, Boolean> flagMap;
  @Column
  private int dieMin;
  @Column
  private int dieMax;

  @ManyToMany(targetEntity = Option.class, fetch = FetchType.EAGER)
  @JsonManagedReference
  @JoinTable( name = "event_options", joinColumns = { @JoinColumn(name = "event_id") },
    inverseJoinColumns = { @JoinColumn(name = "optionId") })
  private Set<Option> options;

// Pseudo Code logic idea
//
//  Event event = eventInProgress
//  Player player = badLuckBrandon
//  for (Entry<String, Boolean> flag : event.flagMap)
//
//  {
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
