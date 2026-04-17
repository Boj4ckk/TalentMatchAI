package com.example.TalentMatchAI.Candidate.controller;

import java.util.List;

public record CandidateResponse (
        Long id,
        String firstName,
        String lastName,
        String email,
        String githubUsername,
        List<String> skills,
        Integer yearsOfExperience,
        String bio

){}
