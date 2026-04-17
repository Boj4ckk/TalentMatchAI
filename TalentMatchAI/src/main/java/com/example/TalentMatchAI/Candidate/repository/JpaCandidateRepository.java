package com.example.TalentMatchAI.Candidate.repository;


import com.example.TalentMatchAI.Candidate.model.Candidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaCandidateRepository implements CandidateRepository {
    private final SpringDataCandidateRepository springData;
   private final CandidateMapper mapper;

    @Override
    public Candidate save(Candidate candidate){
        CandidateEntity candidateEntity = mapper.toEntity(candidate);
        CandidateEntity candidateSaved =  springData.save(candidateEntity);
        return mapper.toModel(candidateSaved);
    }

    @Override
    public Optional<Candidate> findById(Long id){
        return springData.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public List<Candidate> getAll(){
        return  springData.findAll()
                .stream()
                .map(mapper::toModel)
                .toList();

    }

    @Override
    public void deleteCandidateById(Long id){
        springData.deleteById(id);

    }




}
