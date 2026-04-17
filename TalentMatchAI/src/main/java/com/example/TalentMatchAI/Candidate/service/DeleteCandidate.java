package com.example.TalentMatchAI.Candidate.service;


import com.example.TalentMatchAI.Candidate.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCandidate {
    private final CandidateRepository candidateRepository;

    public void execute(Long id){
        candidateRepository.deleteCandidateById(id);
    }
}
