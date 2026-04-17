package com.example.TalentMatchAI.Matching.repository;

import com.example.TalentMatchAI.Matching.model.MatchingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaMatchingResultRepository implements MatchingResultRepository {

    private final SpringDataMatchingResultRepository springData;
    private final MatchingResultMapper mapper;

    @Override
    public MatchingResult save(MatchingResult result) {
        MatchingResultEntity entity = mapper.toEntity(result);
        return mapper.toModel(springData.save(entity));
    }

    @Override
    public Optional<MatchingResult> findById(Long id) {
        return springData.findById(id).map(mapper::toModel);
    }

    @Override
    public List<MatchingResult> getAll() {
        return springData.findAll().stream().map(mapper::toModel).toList();
    }

    @Override
    public List<MatchingResult> findByCandidateId(Long candidateId) {
        return springData.findByCandidateId(candidateId).stream().map(mapper::toModel).toList();
    }

    @Override
    public List<MatchingResult> findByJobOfferId(Long jobOfferId) {
        return springData.findByJobOfferId(jobOfferId).stream().map(mapper::toModel).toList();
    }
}
