package com.hireready.candidate.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SkillProfileResponse {
    private Long candidateId;
    private List<String> primarySkills;
    private List<String> secondarySkills;
    private Integer yearsOfExperience;
    private String seniority;
    private List<String> roles;
    private List<String> industries;

}
