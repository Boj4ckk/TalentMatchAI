package com.example.TalentMatchAI.Matching.kafka;

public record MatchingRequestMessage(Long matchingId, Long candidateId, Long jobOfferId) {}
