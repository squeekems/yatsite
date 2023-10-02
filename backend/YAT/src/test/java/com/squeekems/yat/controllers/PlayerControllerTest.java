package com.squeekems.yat.controllers;

import com.squeekems.yat.entities.Player;
import com.squeekems.yat.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PlayerControllerTest {
  
  @Mock
  PlayerService playerService;
  @InjectMocks
  PlayerController playerController;
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
  }

  @Test
  void postPlayer() throws Exception {
    // Given:

    // When:
    ResultActions result = mockMvc.perform(
        post("/players/player")
            .param("room", String.valueOf(310))
            .param("username", "badLuckBrandon")
    );

    // Then:
    verify(playerService).save(any(Player.class));
    result.andExpect(status().isOk());
  }
}