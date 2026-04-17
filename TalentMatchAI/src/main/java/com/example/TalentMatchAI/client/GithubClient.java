package com.example.TalentMatchAI.client;

import com.example.TalentMatchAI.client.exception.GithubApiException;
import com.example.TalentMatchAI.client.model.GithubRepoResponse;
import com.example.TalentMatchAI.client.model.GithubUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GithubClient {
    private final RestTemplate restTemplate;

    public GithubUserResponse getUser(String username) {
        try {
            return restTemplate.getForObject(
                    "https://api.github.com/users/" + username,
                    GithubUserResponse.class
            );
        } catch (RestClientException e) {
            throw new GithubApiException("Failed to fetch GitHub user: " + username);
        }
    }

    public List<GithubRepoResponse> getRepos(String username) {
        try {
            GithubRepoResponse[] repos = restTemplate.getForObject(
                    "https://api.github.com/users/" + username + "/repos?per_page=100",
                    GithubRepoResponse[].class
            );
            return repos == null ? List.of() : Arrays.asList(repos);
        } catch (RestClientException e) {
            throw new GithubApiException("Failed to fetch GitHub repos for: " + username);
        }
    }
}
