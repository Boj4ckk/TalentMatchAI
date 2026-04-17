package com.example.TalentMatchAI.Candidate.service;

import com.example.TalentMatchAI.Candidate.controller.ImportCandidateRequest;
import com.example.TalentMatchAI.Candidate.model.Candidate;
import com.example.TalentMatchAI.Candidate.repository.CandidateRepository;
import com.example.TalentMatchAI.client.GithubClient;
import com.example.TalentMatchAI.client.model.GithubRepoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImportCandidate {
    private final CandidateRepository candidateRepository;
    private final GithubClient githubClient;

    public Candidate execute(ImportCandidateRequest request) {
        String bio = githubClient.getUser(request.githubUsername()).bio();
        List<GithubRepoResponse> repos = githubClient.getRepos(request.githubUsername());

        List<String> skills = repos.stream()
                .map(GithubRepoResponse::language)
                .filter(lang -> lang != null && !lang.isBlank())
                .collect(Collectors.groupingBy(lang -> lang, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();

        int yearsOfExperience = repos.stream()
                .map(GithubRepoResponse::createdAt)
                .filter(date -> date != null && !date.isBlank())
                .map(date -> LocalDate.parse(date.substring(0, 10)))
                .min(Comparator.naturalOrder())
                .map(oldest -> (int) ChronoUnit.YEARS.between(oldest, LocalDate.now()))
                .orElse(0);

        Candidate candidate = new Candidate(
                null,
                request.firstName(),
                request.lastName(),
                request.email(),
                request.githubUsername(),
                skills,
                yearsOfExperience,
                bio
        );

        return candidateRepository.save(candidate);
    }
}
