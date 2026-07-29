package com.devMoxy.football_quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PlayerAchievement")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Achievement achievement;
}