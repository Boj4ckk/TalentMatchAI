package com.example.TalentMatchAI.client;

import com.example.TalentMatchAI.client.model.OllamaAnalysis;
import com.example.TalentMatchAI.client.model.OllamaRequest;
import com.example.TalentMatchAI.client.model.OllamaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OllamaClient {

    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";
    private static final String MODEL = "llama3.2:3b";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public OllamaAnalysis analyze(String prompt) {
        OllamaRequest request = new OllamaRequest(MODEL, prompt, false);
        OllamaResponse response = restTemplate.postForObject(OLLAMA_URL, request, OllamaResponse.class);
        if (response == null || response.response() == null) {
            throw new RuntimeException("Empty response from Ollama");
        }
        return parseAnalysis(response.response());
    }

    private OllamaAnalysis parseAnalysis(String rawResponse) {
        try {
            String cleaned = rawResponse.trim();
            int start = cleaned.indexOf('{');
            int end = cleaned.lastIndexOf('}');
            if (start != -1 && end != -1) {
                String json = cleaned.substring(start, end + 1);
                Map<?, ?> parsed = objectMapper.readValue(json, Map.class);
                Integer score = parsed.get("score") instanceof Number n ? n.intValue() : 0;
                String analysis = parsed.get("analysis") instanceof String s ? s : cleaned;
                return new OllamaAnalysis(Math.min(100, Math.max(0, score)), analysis);
            }
        } catch (Exception ignored) {
        }
        return new OllamaAnalysis(0, rawResponse);
    }
}
