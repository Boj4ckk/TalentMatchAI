package com.example.TalentMatchAI.Matching.controller;

import com.example.TalentMatchAI.Matching.model.MatchingResult;
import org.springframework.stereotype.Component;

@Component
public class ControllerMatchingMapper {

    public MatchingResponse toResponse(MatchingResult result) {
        return new MatchingResponse(
                result.getId(),
                result.getCandidateId(),
                result.getJobOfferId(),
                result.getScore(),
                result.getAnalysis(),
                result.getStatus(),
                result.getRequestedAt(),
                result.getCompletedAt(),
                result.getErrorMessage()
        );
    }
}
