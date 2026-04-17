package com.example.TalentMatchAI.Candidate.service;


import com.example.TalentMatchAI.Candidate.model.Candidate;
import com.example.TalentMatchAI.Candidate.repository.CandidateEntity;
import com.example.TalentMatchAI.Candidate.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListCandidates {

    private final CandidateRepository candidateRepository;


    public List<Candidate> execute(){
        return candidateRepository.getAll();



    }

}
