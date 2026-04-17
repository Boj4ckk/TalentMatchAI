package com.example.TalentMatchAI.JobOffer.service;

import com.example.TalentMatchAI.JobOffer.model.JobOffer;
import com.example.TalentMatchAI.JobOffer.repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateJobOffer {

    private final JobOfferRepository jobOfferRepository;

    public JobOffer execute(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }
}
