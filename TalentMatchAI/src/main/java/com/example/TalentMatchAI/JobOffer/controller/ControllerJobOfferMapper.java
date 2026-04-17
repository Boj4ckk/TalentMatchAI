package com.example.TalentMatchAI.JobOffer.controller;

import com.example.TalentMatchAI.JobOffer.model.JobOffer;
import org.springframework.stereotype.Component;

@Component
public class ControllerJobOfferMapper {

    public JobOffer toModel(JobOfferRequest request) {
        return new JobOffer(
                null,
                request.title(),
                request.company(),
                request.requiredSkills(),
                request.description(),
                request.location(),
                request.salaryRange(),
                null
        );
    }

    public JobOffer toModel(Long id, JobOfferRequest request) {
        return new JobOffer(
                id,
                request.title(),
                request.company(),
                request.requiredSkills(),
                request.description(),
                request.location(),
                request.salaryRange(),
                null
        );
    }

    public JobOfferResponse toResponse(JobOffer jobOffer) {
        return new JobOfferResponse(
                jobOffer.getId(),
                jobOffer.getTitle(),
                jobOffer.getCompany(),
                jobOffer.getRequiredSkills(),
                jobOffer.getDescription(),
                jobOffer.getLocation(),
                jobOffer.getSalaryRange(),
                jobOffer.getPostedAt()
        );
    }
}
