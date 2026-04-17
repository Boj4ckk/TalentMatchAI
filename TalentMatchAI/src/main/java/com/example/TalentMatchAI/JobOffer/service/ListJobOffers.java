package com.example.TalentMatchAI.JobOffer.service;

import com.example.TalentMatchAI.JobOffer.model.JobOffer;
import com.example.TalentMatchAI.JobOffer.repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListJobOffers {

    private final JobOfferRepository jobOfferRepository;

    public List<JobOffer> execute() {
        return jobOfferRepository.getAll();
    }
}
