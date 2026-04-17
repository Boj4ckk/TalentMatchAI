package com.example.TalentMatchAI.JobOffer.repository;

import com.example.TalentMatchAI.JobOffer.model.JobOffer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaJobOfferRepository implements JobOfferRepository {

    private final SpringDataJobOfferRepository springData;
    private final JobOfferMapper mapper;

    @Override
    public JobOffer save(JobOffer jobOffer) {
        JobOfferEntity entity = mapper.toEntity(jobOffer);
        JobOfferEntity saved = springData.save(entity);
        return mapper.toModel(saved);
    }

    @Override
    public Optional<JobOffer> findById(Long id) {
        return springData.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public List<JobOffer> getAll() {
        return springData.findAll()
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        springData.deleteById(id);
    }
}
