package com.example.TalentMatchAI.JobOffer.service;

import com.example.TalentMatchAI.JobOffer.exception.JobOfferNotFound;
import com.example.TalentMatchAI.JobOffer.model.JobOffer;
import com.example.TalentMatchAI.JobOffer.repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetJobOfferData {

    private final JobOfferRepository jobOfferRepository;

    public JobOffer execute(Long id) {
        return jobOfferRepository.findById(id)
                .orElseThrow(() -> new JobOfferNotFound("Job offer not found with id: " + id));
    }
}
