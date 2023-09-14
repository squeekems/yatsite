package com.squeekems.yat.util.runtime;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Item;
import com.squeekems.yat.entities.Option;
import com.squeekems.yat.util.comparators.OptionComparator;
import com.squeekems.yat.services.EventService;
import com.squeekems.yat.services.OptionService;
import com.squeekems.yat.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Game implements CommandLineRunner {

  @Autowired
  EventService eventService;

  @Autowired
  OptionService optionService;

  Scanner console;

  Long roomId;
  List<String> buildings;
  int dragonHitPoints;
  int playerPointer;
  Event currentEvent;
  List<Player> players;
  List<Event> eventDeck;
  List<Event> eventDiscard;
  List<Item> greaterLoot;
  List<Item> lesserLoot;
  List<Item> lootDiscard;
  String theStory;

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Do you want to start a game in console? (y/n)");

    String answer = console.next();

    if (answer.toLowerCase().equals("y")) {
      if (soutQ1() != 0) {
        eventDeck = eventService.findAll().stream().filter(Event::isCard).toList();
        eventDeck = new ArrayList<>(eventDeck);

        players.sort(new PlayerComparator());

        Set<Integer> setRooms = new HashSet<>();

        for (Player player : players) {
          setRooms.add(player.getRoom());
        }

        List<Integer> rooms = new ArrayList<>(setRooms);

        rooms.add(99);

        soutCustomEvent(
            Constants.getIntroduction(rooms),
            new ArrayList<>(List.of(
                "Continue..."
            ))
        );
        currentEvent = eventDeck.get(new Random().nextInt(eventDeck.size()));
        eventDeck.remove(currentEvent);
        eventDiscard.add(currentEvent);
        soutEvent(currentEvent);
      }
    }
  }

  private Game() {
    buildings = new ArrayList<>(Arrays.asList("tavern", "farmstead", "butchery", "jail",
        "lumber mill", "fletcher", "gypsy cottage", "blacksmith", "barber", "greenhouse",
        "apothecary", "charm chamber", "barracks", "bakery", "library", "cobbler", "tailor",
        "bathhouse", "church", "town hall"));
    dragonHitPoints = 0;
    players = new ArrayList<>();
    eventDeck = new ArrayList<>();
    eventDiscard = new ArrayList<>();
    greaterLoot = new ArrayList<>();
    lesserLoot = new ArrayList<>();
    lootDiscard = new ArrayList<>();
    playerPointer = 0;
    console = new Scanner(System.in);
  }

  public Game(Player... players) {
    this();
    this.players = new ArrayList<>(Arrays.asList(players));
    dragonHitPoints = this.players.size() * 20 + 20;
  }

  public Game(Scanner console) {
    this();
    this.console = console;
  }

  public void addPlayer(Player player) {
    players.add(player);
    player.setPlayerId((long) players.indexOf(player));
    dragonHitPoints += 20;
  }

  public int soutQ1() {
    Event event = eventService.getEventById(309L);
    List<Option> eventOptions = new ArrayList<>(event.getOptions());
    int choice = -1;

    eventOptions.sort(new OptionComparator());

    while(!(choice >= 0 && choice <= eventOptions.size())) {
      System.out.println(event.getPrompt());

      for (int i = 0; i < eventOptions.size(); i++) {
        System.out.println((i + 1) + ") " + eventOptions.get(i).getLabel());
      }

      choice = console.nextInt();
    }

    Player player = new Player(choice - 1);

    System.out.println("What is your name traveler?");

    console.nextLine();

    player.setUsername(console.nextLine());

    if (choice != 0) {
      addPlayer(player);
    }

    soutCustomEvent(
        "Welcome to the tavern, " + player.getUsername() + ".",
        new ArrayList<>(List.of(
            "Continue..."
        ))
    );

    // soutEvent
    event = eventOptions.get(choice - 1).getResult();

    System.out.println(event.getPrompt());

    eventOptions = new ArrayList<>(event.getOptions());

    for (int i = 0; i < eventOptions.size(); i++) {
      System.out.println((i + 1) + ") " + eventOptions.get(i).getLabel());
    }

    choice = console.nextInt();

    return choice;
  }

  public void soutCustomEvent(String prompt, List<String> options) {
    int choice = -1;

    System.out.println(prompt);

    for (int i = 0; i < options.size(); i++) {
      System.out.println((i + 1) + ") " + options.get(i));
    }

    choice = console.nextInt();
  }

  public void soutEvent(Event event) {
    Set<Option> setEventOptions = event.getOptions();
    List<Option> eventOptions = null;
    int choice = -1;

    while (choice != 0) {
      System.out.println(event.getPrompt());

      if (event.getDsPrompt() != null) {
        System.out.println(event.getDsPrompt());
      }

      if (setEventOptions != null && setEventOptions.size() > 0) {
        eventOptions = new ArrayList<>(event.getOptions());

        eventOptions.sort(new OptionComparator());

        for (int i = 0; i < eventOptions.size(); i++) {
          System.out.println((i + 1) + ") " + eventOptions.get(i).getLabel());
        }
      } else {
        System.out.println("1) Continue...");
      }

      choice = console.nextInt();

      if (eventOptions != null &&
          choice > 0 &&
          choice <= setEventOptions.size()) {
        soutEvent(eventOptions.get(choice - 1).getResult());
      } else {
        currentEvent = eventDeck.get(new Random().nextInt(eventDeck.size()));
        eventDeck.remove(currentEvent);
        eventDiscard.add(currentEvent);
        soutEvent(currentEvent);
      }

    }
  }

}
