package com.example.TalentMatchAI.Candidate.controller;

import lombok.Value;

import java.util.List;


public record CandidateRequest(
        String firstName,
        String lastName,
        String email,
        String githubUsername,
        List<String> skills,
        Integer yearsOfExperience,
        String bio
) {}
