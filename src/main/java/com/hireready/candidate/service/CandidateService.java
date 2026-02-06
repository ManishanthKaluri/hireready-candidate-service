package com.hireready.candidate.service;

import org.springframework.stereotype.Service;

import com.hireready.candidate.dto.CandidateRequest;
import com.hireready.candidate.dto.CandidateResponse;

@Service
public interface CandidateService {
    CandidateResponse createCandidate(Long userId, CandidateRequest candidateRequest);
}
