package com.example.TalentMatchAI.Matching.repository;

import com.example.TalentMatchAI.Matching.model.MatchingResult;

import java.util.List;
import java.util.Optional;

public interface MatchingResultRepository {
    MatchingResult save(MatchingResult result);
    Optional<MatchingResult> findById(Long id);
    List<MatchingResult> getAll();
    List<MatchingResult> findByCandidateId(Long candidateId);
    List<MatchingResult> findByJobOfferId(Long jobOfferId);
}
