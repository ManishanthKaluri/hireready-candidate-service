package com.hireready.candidate.service;

import com.hireready.candidate.domain.SkillProfile;
import com.hireready.candidate.dto.CandidateRequest;
import com.hireready.candidate.dto.CandidateResponse;
import com.hireready.candidate.dto.SkillProfileResponse;
import com.hireready.candidate.exception.DuplicateUserException;
import com.hireready.candidate.model.Candidate;
import com.hireready.candidate.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public CandidateResponse createCandidate(Long userId, CandidateRequest request) {

    if (candidateRepository.existsByEmail(request.getEmail())) {
        throw new DuplicateUserException("Candidate already exists with this email");
    }

    if (candidateRepository.findByUserId(userId).isPresent()) {
        throw new DuplicateUserException("Candidate already exists for this user");
    }

    Candidate candidate = Candidate.builder()
            .userId(userId)
            .fullName(request.getFullName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .build();

    Candidate saved = candidateRepository.save(candidate);

    return CandidateResponse.builder()
            .id(saved.getId())
            .userId(saved.getUserId())
            .fullName(saved.getFullName())
            .email(saved.getEmail())
            .phone(saved.getPhone())
            .status(saved.getStatus())
            .build();
}

public SkillProfileResponse getSkillProfile(Long candidateId, Long userId) {

    Candidate candidate = candidateRepository.findById(candidateId)
            .orElseThrow(() -> new RuntimeException("Candidate not found"));

    if (!candidate.getUserId().equals(userId)) {
        throw new RuntimeException("Unauthorized access");
    }

    SkillProfile profile = candidate.getSkillProfile();

    if (profile == null) {
        throw new RuntimeException("Skill profile not available");
    }

    return SkillProfileResponse.builder()
        .candidateId(profile.getCandidate().getId())
        .primarySkills(profile.getPrimarySkills())
        .secondarySkills(profile.getSecondarySkills())
        .yearsOfExperience(profile.getYearsOfExperience())
        .seniority(profile.getSeniority().name())
        .roles(profile.getRoles())
        .industries(profile.getIndustries())
        .build();
}

}
