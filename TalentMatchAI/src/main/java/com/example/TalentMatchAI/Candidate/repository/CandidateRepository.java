package com.example.TalentMatchAI.Candidate.repository;

import com.example.TalentMatchAI.Candidate.model.Candidate;
import java.util.List;
import java.util.Optional;

public interface CandidateRepository {
    Candidate save(Candidate candidate);
    Optional<Candidate> findById(Long id);
    List<Candidate> getAll();
    void deleteCandidateById(Long id);



}
