package com.devMoxy.football_quiz.service;

import com.devMoxy.football_quiz.dto.PlayerCreateDTO;
import com.devMoxy.football_quiz.dto.PlayerDTO;
import com.devMoxy.football_quiz.entity.Player;
import com.devMoxy.football_quiz.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    private PlayerDTO convertToDto(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(player.getId());
        dto.setName(player.getName());
        dto.setImageUrl(player.getImageUrl());
        return dto;
    }

    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDTO> dtoList = new ArrayList<>();

        for (Player player : players) {
            dtoList.add(convertToDto(player));
        }
        return dtoList;
    }

    public PlayerDTO createPlayer(PlayerCreateDTO dto) {
        Player player = new Player();
        player.setName(dto.getName());
        player.setImageUrl(dto.getImageUrl());
        Player saved = playerRepository.save(player);
        return convertToDto(saved);
    }
}
