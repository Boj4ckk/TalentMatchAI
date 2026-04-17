package com.example.TalentMatchAI.Candidate.exception;


public class CandidateNotFound extends RuntimeException {
    public CandidateNotFound(String message) {
        super(message);  // appelle RuntimeException(message)
    }
}