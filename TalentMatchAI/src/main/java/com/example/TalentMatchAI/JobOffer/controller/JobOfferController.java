package com.example.TalentMatchAI.JobOffer.controller;

import com.example.TalentMatchAI.JobOffer.model.JobOffer;
import com.example.TalentMatchAI.JobOffer.service.*;
import com.example.TalentMatchAI.JobOffer.service.UpdateJobOfferData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-offers")
@RequiredArgsConstructor
public class JobOfferController {

    private final CreateJobOffer createJobOffer;
    private final ListJobOffers listJobOffers;
    private final GetJobOfferData getJobOfferData;
    private final UpdateJobOfferData updateJobOfferData;
    private final DeleteJobOffer deleteJobOffer;
    private final ControllerJobOfferMapper jobOfferMapper;

    @PostMapping
    public ResponseEntity<JobOfferResponse> createJobOffer(@RequestBody JobOfferRequest request) {
        JobOffer jobOffer = createJobOffer.execute(jobOfferMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(jobOfferMapper.toResponse(jobOffer));
    }

    @GetMapping
    public ResponseEntity<List<JobOfferResponse>> getAllJobOffers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        listJobOffers.execute()
                                .stream()
                                .map(jobOfferMapper::toResponse)
                                .toList()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOfferResponse> getJobOfferById(@PathVariable Long id) {
        JobOffer jobOffer = getJobOfferData.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(jobOfferMapper.toResponse(jobOffer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobOfferResponse> updateJobOffer(@PathVariable Long id, @RequestBody JobOfferRequest request) {
        JobOffer jobOffer = updateJobOfferData.execute(id, jobOfferMapper.toModel(id, request));
        return ResponseEntity.status(HttpStatus.OK).body(jobOfferMapper.toResponse(jobOffer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobOffer(@PathVariable Long id) {
        deleteJobOffer.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
