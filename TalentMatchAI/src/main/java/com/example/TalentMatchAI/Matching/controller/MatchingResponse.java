package com.example.TalentMatchAI.Matching.controller;

import com.example.TalentMatchAI.Matching.model.MatchingStatus;

import java.time.LocalDateTime;

public record MatchingResponse(
        Long id,
        Long candidateId,
        Long jobOfferId,
        Integer score,
        String analysis,
        MatchingStatus status,
        LocalDateTime requestedAt,
        LocalDateTime completedAt,
        String errorMessage
) {}
