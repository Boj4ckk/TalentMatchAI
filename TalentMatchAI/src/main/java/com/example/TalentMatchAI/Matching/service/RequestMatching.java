package com.example.TalentMatchAI.Matching.service;

import com.example.TalentMatchAI.Matching.kafka.MatchingKafkaProducer;
import com.example.TalentMatchAI.Matching.kafka.MatchingRequestMessage;
import com.example.TalentMatchAI.Matching.model.MatchingResult;
import com.example.TalentMatchAI.Matching.model.MatchingStatus;
import com.example.TalentMatchAI.Matching.repository.MatchingResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestMatching {

    private final MatchingResultRepository repository;
    private final MatchingKafkaProducer producer;

    public MatchingResult execute(Long candidateId, Long jobOfferId) {
        MatchingResult result = new MatchingResult(
                null, candidateId, jobOfferId,
                null, null, MatchingStatus.PENDING,
                null, null, null
        );
        MatchingResult saved = repository.save(result);
        producer.send(new MatchingRequestMessage(saved.getId(), candidateId, jobOfferId));
        return saved;
    }
}
