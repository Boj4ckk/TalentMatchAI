package com.example.TalentMatchAI.Matching.kafka;

import com.example.TalentMatchAI.Candidate.model.Candidate;
import com.example.TalentMatchAI.Candidate.repository.CandidateRepository;
import com.example.TalentMatchAI.JobOffer.model.JobOffer;
import com.example.TalentMatchAI.JobOffer.repository.JobOfferRepository;
import com.example.TalentMatchAI.Matching.model.MatchingResult;
import com.example.TalentMatchAI.Matching.model.MatchingStatus;
import com.example.TalentMatchAI.Matching.repository.MatchingResultRepository;
import com.example.TalentMatchAI.client.OllamaClient;
import com.example.TalentMatchAI.client.model.OllamaAnalysis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class MatchingKafkaConsumer {

    private final MatchingResultRepository matchingResultRepository;
    private final CandidateRepository candidateRepository;
    private final JobOfferRepository jobOfferRepository;
    private final OllamaClient ollamaClient;

    @KafkaListener(topics = "matching-requests", groupId = "talent-match-group")
    public void consume(MatchingRequestMessage message) {
        log.info("Processing matching request: matchingId={}", message.matchingId());

        MatchingResult result = matchingResultRepository.findById(message.matchingId())
                .orElseThrow(() -> new RuntimeException("MatchingResult not found: " + message.matchingId()));

        matchingResultRepository.save(new MatchingResult(
                result.getId(), result.getCandidateId(), result.getJobOfferId(),
                null, null, MatchingStatus.PROCESSING,
                result.getRequestedAt(), null, null
        ));

        try {
            Candidate candidate = candidateRepository.findById(message.candidateId())
                    .orElseThrow(() -> new RuntimeException("Candidate not found: " + message.candidateId()));
            JobOffer jobOffer = jobOfferRepository.findById(message.jobOfferId())
                    .orElseThrow(() -> new RuntimeException("JobOffer not found: " + message.jobOfferId()));

            String prompt = buildPrompt(candidate, jobOffer);
            OllamaAnalysis analysis = ollamaClient.analyze(prompt);

            matchingResultRepository.save(new MatchingResult(
                    result.getId(), result.getCandidateId(), result.getJobOfferId(),
                    analysis.score(), analysis.analysis(), MatchingStatus.COMPLETED,
                    result.getRequestedAt(), LocalDateTime.now(), null
            ));
            log.info("Matching completed: matchingId={}, score={}", message.matchingId(), analysis.score());

        } catch (Exception e) {
            log.error("Matching failed: matchingId={}, error={}", message.matchingId(), e.getMessage());
            matchingResultRepository.save(new MatchingResult(
                    result.getId(), result.getCandidateId(), result.getJobOfferId(),
                    null, null, MatchingStatus.FAILED,
                    result.getRequestedAt(), LocalDateTime.now(), e.getMessage()
            ));
        }
    }

    private String buildPrompt(Candidate candidate, JobOffer jobOffer) {
        return """
                Analyze the compatibility between this candidate and job offer.

                Candidate:
                - Name: %s %s
                - Skills: %s
                - Years of experience: %d
                - Bio: %s

                Job Offer:
                - Title: %s
                - Company: %s
                - Required Skills: %s
                - Description: %s
                - Location: %s

                Provide a compatibility score from 0 to 100 and a brief analysis.
                Respond ONLY with valid JSON in this exact format:
                {"score": <number between 0 and 100>, "analysis": "<your analysis text>"}
                """.formatted(
                candidate.getFirstName(), candidate.getLastName(),
                candidate.getSkills(),
                candidate.getYearsOfExperience(),
                candidate.getBio() != null ? candidate.getBio() : "N/A",
                jobOffer.getTitle(),
                jobOffer.getCompany(),
                jobOffer.getRequiredSkills(),
                jobOffer.getDescription(),
                jobOffer.getLocation()
        );
    }
}
