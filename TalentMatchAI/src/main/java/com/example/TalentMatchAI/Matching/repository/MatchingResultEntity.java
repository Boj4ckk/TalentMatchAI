package com.example.TalentMatchAI.Matching.repository;

import com.example.TalentMatchAI.Matching.model.MatchingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "matching_result")
public class MatchingResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false)
    private Long jobOfferId;

    @Column
    private Integer score;

    @Column(columnDefinition = "TEXT")
    private String analysis;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchingStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime requestedAt;

    @Column
    private LocalDateTime completedAt;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;
}
