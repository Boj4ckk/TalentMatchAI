package com.example.TalentMatchAI.Matching.service;

import com.example.TalentMatchAI.Matching.exception.MatchingResultNotFound;
import com.example.TalentMatchAI.Matching.model.MatchingResult;
import com.example.TalentMatchAI.Matching.repository.MatchingResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMatchingResult {

    private final MatchingResultRepository repository;

    public MatchingResult execute(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MatchingResultNotFound("Matching result not found with id: " + id));
    }
}
