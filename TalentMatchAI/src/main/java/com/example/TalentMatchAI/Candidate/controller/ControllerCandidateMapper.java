package com.example.TalentMatchAI.Candidate.controller;

import com.example.TalentMatchAI.Candidate.model.Candidate;
import org.springframework.stereotype.Component;

@Component
public class ControllerCandidateMapper {
    //Overload to convert request -> model
    public Candidate toModel(Long id, CandidateRequest candidateRequest){
        return new Candidate(
                id,
                candidateRequest.firstName(),
                candidateRequest.lastName(),
                candidateRequest.email(),
                candidateRequest.githubUsername(),
                candidateRequest.skills(),
                candidateRequest.yearsOfExperience(),
                candidateRequest.bio()
        );
    }

    public Candidate toModel(CandidateRequest candidateRequest){
        return new Candidate(
                null,
                candidateRequest.firstName(),
                candidateRequest.lastName(),
                candidateRequest.email(),
                candidateRequest.githubUsername(),
                candidateRequest.skills(),
                candidateRequest.yearsOfExperience(),
                candidateRequest.bio()


        );
    }

    public CandidateResponse toResponse(Candidate candidate){
        return new CandidateResponse(
                candidate.getId(),
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getEmail(),
                candidate.getGithubUsername(),
                candidate.getSkills(),
                candidate.getYearsOfExperience(),
                candidate.getBio()
        );
    }
}
