package com.example.TalentMatchAI.Candidate.repository;

import com.example.TalentMatchAI.Candidate.controller.CandidateRequest;
import com.example.TalentMatchAI.Candidate.controller.CandidateResponse;
import org.springframework.stereotype.Component;

import com.example.TalentMatchAI.Candidate.model.Candidate;
@Component
public class CandidateMapper {

    public  CandidateEntity toEntity(Candidate candidate){
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setId(candidate.getId());
        candidateEntity.setFirstName(candidate.getFirstName());
        candidateEntity.setLastName(candidate.getLastName());
        candidateEntity.setEmail(candidate.getEmail());
        candidateEntity.setGithubUsername(candidate.getGithubUsername());
        candidateEntity.setYearsOfExperience(candidate.getYearsOfExperience());
        candidateEntity.setSkills(candidate.getSkills());
        candidateEntity.setBio(candidate.getBio());
        return candidateEntity;

    }

    public Candidate toModel(CandidateEntity candidateEntity){
        return new Candidate(
                candidateEntity.getId(),
                candidateEntity.getFirstName(),
                candidateEntity.getLastName(),
                candidateEntity.getEmail(),
                candidateEntity.getGithubUsername(),
                candidateEntity.getSkills(),
                candidateEntity.getYearsOfExperience(),
                candidateEntity.getBio()
        );
    }






}
