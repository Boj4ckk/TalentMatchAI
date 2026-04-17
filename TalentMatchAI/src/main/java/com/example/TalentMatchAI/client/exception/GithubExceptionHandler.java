package com.example.TalentMatchAI.client.exception;

import com.example.TalentMatchAI.common.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GithubExceptionHandler {

    @ExceptionHandler(GithubApiException.class)
    public ResponseEntity<?> handleGithubApiException(GithubApiException ex) {
        ErrorResponse error = new ErrorResponse(
                "GITHUB_API_ERROR",
                ex.getMessage(),
                HttpStatus.BAD_GATEWAY.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
    }
}
