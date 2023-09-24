package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Game;
import com.squeekems.yat.entities.Option;
import com.squeekems.yat.entities.Player;
import com.squeekems.yat.services.EventService;
import com.squeekems.yat.services.GameService;
import com.squeekems.yat.services.PlayerService;
import com.squeekems.yat.util.Constants;
import com.squeekems.yat.util.comparators.OptionComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/games")
public class GameController {

  @Autowired
  EventService eventService;

  @Autowired
  GameService gameService;

  @Autowired
  PlayerService playerService;

  @RequestMapping("/start")
  public Long start() {
    Game game = new Game();
    gameService.save(game);
    return game.getGameId();
  }

  @RequestMapping("/events/get")
  public Event getEvent(@RequestParam("id") Long id) {
    Event event = eventService.getById(id);
    Set<Option> setOptions = event.getOptions();
    List<Option> options = null;

    if (setOptions != null && setOptions.size() > 0) {
      options = new ArrayList<>(setOptions);
      options.sort(new OptionComparator());
    } else {
      options = new ArrayList<>(List.of(new Option(Constants.optionContinue)));
      event.setOptions(new HashSet<>(options));
      eventService.save(event);
    }

    return event;
  }

  public Event handleWhatIsYourName(@RequestParam("gameId") Long gameId,
                                    @RequestParam("prompt") String prompt,
                                    @RequestParam("dsPrompt") String dsPrompt,
                                    @RequestParam("username") String username) {
    Game game = gameService.getById(gameId);
    Player newPlayer = new Player(Integer.parseInt(dsPrompt), username);
    game.addPlayer(newPlayer);
    gameService.save(game);
    checkQueues(prompt, newPlayer);

    return new Event(
        String.format(Constants.fWelcomeToTavern, username)
    );
  }

  public Event handleContinue(@RequestParam("gameId") Long gameId,
                              @RequestParam("prompt") String prompt,
                              @RequestParam("dsPrompt") String dsPrompt) {
    Game game = gameService.getById(gameId);
    Player player = game.getPlayers().size() > 0 && game.getPlayerPointer() >= 0 &&
        game.getPlayerPointer() < game.getPlayers().size() ?
        game.getPlayers().get(game.getPlayerPointer()) : null;

    if (prompt.equals(Constants.whatIsYourName)) {
      Player newPlayer = new Player(Integer.parseInt(dsPrompt));
//        console.nextLine();
//        newPlayer.setUsername(console.nextLine());
      game.addPlayer(newPlayer);
      gameService.save(game);
      checkQueues(prompt, player);

      return new Event(
          String.format(Constants.fWelcomeToTavern, newPlayer.getUsername())
      );
    } else {
      checkQueues(prompt, player);

      return new Event("We are figuring it out.", false, new HashSet<>(List.of(new Option(Constants.optionContinue))));
//        System.out.println(String.format(Constants.fOption, 1, Constants.optionContinue));
//        choice = console.nextInt();
    }
  }

  public Event handleChoice(@RequestParam("gameId") Long gameId,
                            @RequestParam("eventId") Long eventId,
                            @RequestParam("choice") int choice) {
    Game game = gameService.getById(gameId);
    Event event = eventService.getById(eventId);
    Set<Option> setOptions = event.getOptions();
    List<Option> options = null;
    Player player = game.getPlayers().size() > 0 && game.getPlayerPointer() >= 0 &&
        game.getPlayerPointer() < game.getPlayers().size() ?
        game.getPlayers().get(game.getPlayerPointer()) : null;

    if (setOptions != null && setOptions.size() > 0) {
      options = new ArrayList<>(setOptions);
      options.sort(new OptionComparator());
    }

    if (options != null) {
      if (event.getPrompt().equals(Constants.youAreInATavern)) {
        checkQueues(event.getPrompt(), player);

        return new Event(Constants.whatIsYourName, String.valueOf(choice - 1));
      }

      checkQueues(event.getPrompt(), player);

      return options.get(choice - 1).getResult();
    } else {
      if (event.getPrompt().equals(Constants.whatIsYourName)) {
        Player newPlayer = new Player(Integer.parseInt(event.getDsPrompt()));
//        console.nextLine();
//        newPlayer.setUsername(console.nextLine());
        game.addPlayer(newPlayer);
        gameService.save(game);
        checkQueues(event.getPrompt(), player);

        return new Event(
            String.format(Constants.fWelcomeToTavern, newPlayer.getUsername())
        );
      } else {
        checkQueues(event.getPrompt(), player);

        return new Event("We are figuring it out.", false, new HashSet<>(List.of(new Option(Constants.optionContinue))));
//        System.out.println(String.format(Constants.fOption, 1, Constants.optionContinue));
//        choice = console.nextInt();
      }
    }
  }

  @RequestMapping("/end")
  public String end(@RequestParam("id") Long id) {
    try {
      gameService.delete(id);
      return String.format(Constants.fDeleting, "game(" + id + ")");
    } catch (Exception e) {
      e.printStackTrace();
      return e.toString();
    }
  }

  private void discardCurrentEventCard(Game game) {
    if (game.getCurrentEvent() != null) {
      List<Event> discard = new ArrayList<>(game.getDiscard());
      discard.add(game.getCurrentEvent());
      game.setDiscard(new HashSet<>(discard));
      game.setCurrentEvent(null);
    }
  }

  private void checkQueues(String prompt, Player player) {
    if (player != null) {
      if (prompt.contains(Constants.skipQueue)) {
        player.setSkipCounter(player.getSkipCounter() + 1);
        playerService.save(player);
      }
    }
  }
}
