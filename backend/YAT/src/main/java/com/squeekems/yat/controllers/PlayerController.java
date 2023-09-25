package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Event;
import com.squeekems.yat.entities.Player;
import com.squeekems.yat.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

  @Autowired
  private PlayerService playerService;

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/post")
  public void postPlayer(@RequestParam("room") int room,
                         @RequestParam("username") String username) {
    Player player = new Player(room, username);
    playerService.save(player);
  }
}
