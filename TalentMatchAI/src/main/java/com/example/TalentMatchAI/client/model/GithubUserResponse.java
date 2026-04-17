package com.example.TalentMatchAI.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubUserResponse(
        String login,
        String name,
        String bio,
        String email,
        @JsonProperty("public_repos") int publicRepos,
        String location
) {}
