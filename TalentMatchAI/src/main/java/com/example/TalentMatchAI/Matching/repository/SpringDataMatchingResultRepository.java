package com.example.TalentMatchAI.Matching.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataMatchingResultRepository extends JpaRepository<MatchingResultEntity, Long> {
    List<MatchingResultEntity> findByCandidateId(Long candidateId);
    List<MatchingResultEntity> findByJobOfferId(Long jobOfferId);
}
