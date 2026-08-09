package com.devMoxy.football_quiz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AchievementCreateDTO {
    @NotBlank
    private String description;

    @NotBlank
    private String imageUrl;
}
