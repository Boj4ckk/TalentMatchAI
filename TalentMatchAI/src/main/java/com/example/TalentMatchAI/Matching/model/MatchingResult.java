package com.example.TalentMatchAI.Matching.model;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class MatchingResult {
    Long id;
    Long candidateId;
    Long jobOfferId;
    Integer score;
    String analysis;
    MatchingStatus status;
    LocalDateTime requestedAt;
    LocalDateTime completedAt;
    String errorMessage;
}
