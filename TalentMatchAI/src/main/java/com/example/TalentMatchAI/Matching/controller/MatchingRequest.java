package com.example.TalentMatchAI.Matching.controller;

import jakarta.validation.constraints.NotNull;

public record MatchingRequest(
        @NotNull Long candidateId,
        @NotNull Long jobOfferId
) {}
