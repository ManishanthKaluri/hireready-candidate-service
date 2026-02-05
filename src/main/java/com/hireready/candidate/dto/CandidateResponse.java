package com.hireready.candidate.dto;

import com.hireready.candidate.model.CandidateStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CandidateResponse {
    private Long id;
    private Long userId;
    private String fullName;
    private String email;
    private String phone;
    private CandidateStatus status;
}
