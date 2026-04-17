package com.example.TalentMatchAI.Candidate.controller;


import com.example.TalentMatchAI.Candidate.model.Candidate;
import com.example.TalentMatchAI.Candidate.service.GetCandidateData;
import com.example.TalentMatchAI.Candidate.service.ListCandidates;
import com.example.TalentMatchAI.Candidate.service.RegisterCandidate;
import com.example.TalentMatchAI.Candidate.service.DeleteCandidate;
import com.example.TalentMatchAI.Candidate.service.ImportCandidate;
import com.example.TalentMatchAI.Candidate.service.UpdateCandidateData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final RegisterCandidate registerCandidate;
    private final UpdateCandidateData updateCandidateData;
    private final ListCandidates listCandidates;
    private final GetCandidateData getCandidateData;
    private final DeleteCandidate deleteCandidateById;
    private final ImportCandidate importCandidate;
    private final ControllerCandidateMapper candidateMapper;



    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidate(@RequestBody CandidateRequest request){

        Candidate candidate = registerCandidate.execute( candidateMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateMapper.toResponse(candidate));

    }
    @PutMapping("/{id}")
    public ResponseEntity<CandidateResponse> updateCandidate(@PathVariable Long id, @RequestBody CandidateRequest request){
        Candidate candidate = updateCandidateData.execute(id, candidateMapper.toModel(id, request));
        return ResponseEntity.status(HttpStatus.OK).body(candidateMapper.toResponse(candidate));

    }

    @GetMapping
    public ResponseEntity<List<CandidateResponse>> getAllCandidate(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        listCandidates.execute()
                        .stream()
                        .map(candidateMapper::toResponse)
                        .toList()
                );


    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getCandidateById(@PathVariable Long id){
        Candidate candidate = getCandidateData.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(candidateMapper.toResponse(candidate));

    }
    @PostMapping("/import")
    public ResponseEntity<CandidateResponse> importCandidate(@RequestBody ImportCandidateRequest request){
        Candidate candidate = importCandidate.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateMapper.toResponse(candidate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidateById(@PathVariable Long id){
        deleteCandidateById.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }



}
