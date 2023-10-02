package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Player;
import com.squeekems.yat.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.squeekems.yat.util.Constants.CORS_URL;

@RestController
@RequestMapping("/players")
public class PlayerController {

  @Autowired
  private PlayerService playerService;

  @CrossOrigin(origins = CORS_URL)
  @PostMapping("/player")
  public void postPlayer(@RequestParam("room") int room,
                         @RequestParam("username") String username) {
    Player player = new Player(room, username);
    playerService.save(player);
  }
}
