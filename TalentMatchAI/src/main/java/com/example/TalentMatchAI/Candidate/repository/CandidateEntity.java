package com.example.TalentMatchAI.Candidate.repository;

import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;

    @Column(unique = true)
    private String githubUsername;

    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="candidate_skills", joinColumns = @JoinColumn(name="candidate_id"))
    @Column(name="skill")
    private List<String> skills;

    @Column(nullable = false)
    @Min(0)
    private Integer yearsOfExperience;

    @Column(nullable = true)
    private String bio;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


}
