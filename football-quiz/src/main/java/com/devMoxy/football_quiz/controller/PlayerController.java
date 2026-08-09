package com.devMoxy.football_quiz.controller;

import com.devMoxy.football_quiz.dto.PlayerCreateDTO;
import com.devMoxy.football_quiz.dto.PlayerDTO;
import com.devMoxy.football_quiz.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/api/players")
    public List<PlayerDTO> getPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping("/api/players")
    public PlayerDTO postPlayer(@Valid @RequestBody PlayerCreateDTO dto) {
        return playerService.createPlayer(dto);
    }
}
