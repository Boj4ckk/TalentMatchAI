package com.example.TalentMatchAI.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubRepoResponse(
        String name,
        String language,
        @JsonProperty("created_at") String createdAt
) {}
