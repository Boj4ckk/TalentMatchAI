package com.example.TalentMatchAI.JobOffer.repository;

import com.example.TalentMatchAI.JobOffer.model.JobOffer;

import java.util.List;
import java.util.Optional;

public interface JobOfferRepository {
    JobOffer save(JobOffer jobOffer);
    Optional<JobOffer> findById(Long id);
    List<JobOffer> getAll();
    void deleteById(Long id);
}
