package com.example.TalentMatchAI.JobOffer.service;

import com.example.TalentMatchAI.JobOffer.repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteJobOffer {

    private final JobOfferRepository jobOfferRepository;

    public void execute(Long id) {
        jobOfferRepository.deleteById(id);
    }
}
