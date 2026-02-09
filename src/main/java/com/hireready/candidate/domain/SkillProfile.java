package com.hireready.candidate.domain;

import lombok.*;

import java.util.List;

import com.hireready.candidate.model.Candidate;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SkillProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ElementCollection
    private List<String> primarySkills;

    @ElementCollection
    private List<String> secondarySkills;

    private Integer yearsOfExperience;

    @Enumerated(EnumType.STRING)
    private Seniority seniority;

    @ElementCollection
    private List<String> roles;

    @ElementCollection
    private List<String> industries;
}
