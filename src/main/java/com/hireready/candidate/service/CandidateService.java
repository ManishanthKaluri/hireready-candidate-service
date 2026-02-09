package com.hireready.candidate.service;

import org.springframework.stereotype.Service;

import com.hireready.candidate.dto.CandidateRequest;
import com.hireready.candidate.dto.CandidateResponse;
import com.hireready.candidate.dto.SkillProfileResponse;

@Service
public interface CandidateService {
    CandidateResponse createCandidate(Long userId, CandidateRequest candidateRequest);
    SkillProfileResponse getSkillProfile(Long candidateId, Long userId);
}
