package com.example.TalentMatchAI.JobOffer.model;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class JobOffer {
    Long id;
    String title;
    String company;
    List<String> requiredSkills;
    String description;
    String location;
    String salaryRange;
    LocalDateTime postedAt;
}
