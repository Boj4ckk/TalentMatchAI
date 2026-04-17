package com.example.TalentMatchAI.Candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataCandidateRepository extends JpaRepository<CandidateEntity,Long> {
}
