package com.example.TalentMatchAI.Matching.repository;

import com.example.TalentMatchAI.Matching.model.MatchingResult;
import org.springframework.stereotype.Component;

@Component
public class MatchingResultMapper {

    public MatchingResultEntity toEntity(MatchingResult result) {
        MatchingResultEntity entity = new MatchingResultEntity();
        entity.setId(result.getId());
        entity.setCandidateId(result.getCandidateId());
        entity.setJobOfferId(result.getJobOfferId());
        entity.setScore(result.getScore());
        entity.setAnalysis(result.getAnalysis());
        entity.setStatus(result.getStatus());
        entity.setCompletedAt(result.getCompletedAt());
        entity.setErrorMessage(result.getErrorMessage());
        return entity;
    }

    public MatchingResult toModel(MatchingResultEntity entity) {
        return new MatchingResult(
                entity.getId(),
                entity.getCandidateId(),
                entity.getJobOfferId(),
                entity.getScore(),
                entity.getAnalysis(),
                entity.getStatus(),
                entity.getRequestedAt(),
                entity.getCompletedAt(),
                entity.getErrorMessage()
        );
    }
}
