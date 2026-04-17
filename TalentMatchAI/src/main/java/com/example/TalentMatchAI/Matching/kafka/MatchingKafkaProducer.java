package com.example.TalentMatchAI.Matching.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MatchingKafkaProducer {

    private static final String TOPIC = "matching-requests";

    private final KafkaTemplate<String, MatchingRequestMessage> kafkaTemplate;

    public void send(MatchingRequestMessage message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
