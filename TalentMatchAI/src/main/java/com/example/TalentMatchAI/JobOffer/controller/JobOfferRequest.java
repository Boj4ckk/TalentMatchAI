package com.example.TalentMatchAI.JobOffer.controller;

import java.util.List;

public record JobOfferRequest(
        String title,
        String company,
        List<String> requiredSkills,
        String description,
        String location,
        String salaryRange
) {}
