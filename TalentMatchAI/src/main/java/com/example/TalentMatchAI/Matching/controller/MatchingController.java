package com.example.TalentMatchAI.Matching.controller;

import com.example.TalentMatchAI.Matching.model.MatchingResult;
import com.example.TalentMatchAI.Matching.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matching")
@RequiredArgsConstructor
public class MatchingController {

    private final RequestMatching requestMatching;
    private final ListMatchingResults listMatchingResults;
    private final GetMatchingResult getMatchingResult;
    private final GetResultsByCandidate getResultsByCandidate;
    private final GetResultsByJobOffer getResultsByJobOffer;
    private final ControllerMatchingMapper mapper;

    @PostMapping("/analyze")
    public ResponseEntity<MatchingResponse> analyze(@Valid @RequestBody MatchingRequest request) {
        MatchingResult result = requestMatching.execute(request.candidateId(), request.jobOfferId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.toResponse(result));
    }

    @GetMapping("/results")
    public ResponseEntity<List<MatchingResponse>> getAllResults() {
        return ResponseEntity.ok(
                listMatchingResults.execute().stream().map(mapper::toResponse).toList()
        );
    }

    @GetMapping("/results/{id}")
    public ResponseEntity<MatchingResponse> getResultById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(getMatchingResult.execute(id)));
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<MatchingResponse>> getResultsByCandidate(@PathVariable Long candidateId) {
        return ResponseEntity.ok(
                getResultsByCandidate.execute(candidateId).stream().map(mapper::toResponse).toList()
        );
    }

    @GetMapping("/job/{jobOfferId}")
    public ResponseEntity<List<MatchingResponse>> getResultsByJobOffer(@PathVariable Long jobOfferId) {
        return ResponseEntity.ok(
                getResultsByJobOffer.execute(jobOfferId).stream().map(mapper::toResponse).toList()
        );
    }
}
