package com.example.TalentMatchAI.Candidate.service;

import com.example.TalentMatchAI.Candidate.controller.CandidateRequest;
import com.example.TalentMatchAI.Candidate.controller.CandidateResponse;
import com.example.TalentMatchAI.Candidate.model.Candidate;
import com.example.TalentMatchAI.Candidate.repository.CandidateMapper;
import com.example.TalentMatchAI.Candidate.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCandidate {
    private final CandidateRepository candidateRepository;

    public Candidate execute(Candidate candidate){
        return candidateRepository.save(candidate);

    }
}
