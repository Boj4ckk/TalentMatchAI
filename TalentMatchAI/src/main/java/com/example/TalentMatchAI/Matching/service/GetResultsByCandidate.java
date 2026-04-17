package com.example.TalentMatchAI.Matching.service;

import com.example.TalentMatchAI.Matching.model.MatchingResult;
import com.example.TalentMatchAI.Matching.repository.MatchingResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetResultsByCandidate {

    private final MatchingResultRepository repository;

    public List<MatchingResult> execute(Long candidateId) {
        return repository.findByCandidateId(candidateId);
    }
}
