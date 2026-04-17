package com.example.TalentMatchAI.Candidate.exception;

import com.example.TalentMatchAI.JobOffer.exception.JobOfferNotFound;
import com.example.TalentMatchAI.Matching.exception.MatchingResultNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.TalentMatchAI.common.exception.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CandidateNotFound.class)
    public ResponseEntity<?> handleCandidateNotFound(CandidateNotFound ex){
        ErrorResponse error = new ErrorResponse(
                "CANDIDATE_NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(JobOfferNotFound.class)
    public ResponseEntity<?> handleJobOfferNotFound(JobOfferNotFound ex){
        ErrorResponse error = new ErrorResponse(
                "JOB_OFFER_NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MatchingResultNotFound.class)
    public ResponseEntity<?> handleMatchingResultNotFound(MatchingResultNotFound ex){
        ErrorResponse error = new ErrorResponse(
                "MATCHING_RESULT_NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
