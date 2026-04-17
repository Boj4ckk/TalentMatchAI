package com.example.TalentMatchAI.Candidate.controller;

public record ImportCandidateRequest(
        String githubUsername,
        String email,
        String firstName,
        String lastName
) {}
