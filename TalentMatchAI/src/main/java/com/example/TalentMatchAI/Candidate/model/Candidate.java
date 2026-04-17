package com.example.TalentMatchAI.Candidate.model;
import lombok.Value;
import java.util.List;

@Value
public class Candidate {
     Long id;
     String firstName;
     String lastName;
     String email;
     String githubUsername;
     List<String> skills;
     Integer yearsOfExperience;
     String bio;

}
