package com.hireready.candidate.service;

import com.hireready.candidate.dto.CandidateRequest;
import com.hireready.candidate.dto.CandidateResponse;
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
    public CandidateResponse createCandidate(CandidateRequest request) {

        if (candidateRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException("Candidate already exists with this email");
        }

        if (candidateRepository.findByUserId(request.getUserId()).isPresent()) {
            throw new DuplicateUserException("Candidate already exists for this user");
        }

        Candidate candidate = Candidate.builder()
                .userId(request.getUserId())
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

}
