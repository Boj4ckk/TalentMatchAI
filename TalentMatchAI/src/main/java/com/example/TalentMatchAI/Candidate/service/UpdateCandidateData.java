package com.example.TalentMatchAI.Candidate.service;

import com.example.TalentMatchAI.Candidate.exception.CandidateNotFound;
import com.example.TalentMatchAI.Candidate.model.Candidate;
import com.example.TalentMatchAI.Candidate.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCandidateData {
    private final CandidateRepository candidateRepository;


    public Candidate execute(Long id, Candidate candidate){
        candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFound("Candidate not found with id: " + id));
        return candidateRepository.save(candidate);
    }
}
