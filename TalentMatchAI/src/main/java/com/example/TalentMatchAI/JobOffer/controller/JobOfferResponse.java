package com.example.TalentMatchAI.JobOffer.controller;

import java.time.LocalDateTime;
import java.util.List;

public record JobOfferResponse(
        Long id,
        String title,
        String company,
        List<String> requiredSkills,
        String description,
        String location,
        String salaryRange,
        LocalDateTime postedAt
) {}
