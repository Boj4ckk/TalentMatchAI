package com.example.TalentMatchAI.JobOffer.repository;

import com.example.TalentMatchAI.JobOffer.model.JobOffer;
import org.springframework.stereotype.Component;

@Component
public class JobOfferMapper {

    public JobOfferEntity toEntity(JobOffer jobOffer) {
        JobOfferEntity entity = new JobOfferEntity();
        entity.setId(jobOffer.getId());
        entity.setTitle(jobOffer.getTitle());
        entity.setCompany(jobOffer.getCompany());
        entity.setRequiredSkills(jobOffer.getRequiredSkills());
        entity.setDescription(jobOffer.getDescription());
        entity.setLocation(jobOffer.getLocation());
        entity.setSalaryRange(jobOffer.getSalaryRange());
        return entity;
    }

    public JobOffer toModel(JobOfferEntity entity) {
        return new JobOffer(
                entity.getId(),
                entity.getTitle(),
                entity.getCompany(),
                entity.getRequiredSkills(),
                entity.getDescription(),
                entity.getLocation(),
                entity.getSalaryRange(),
                entity.getPostedAt()
        );
    }
}
