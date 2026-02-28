package com.hireready.candidate.controller;

import com.hireready.candidate.client.InterviewClient;
import com.hireready.candidate.domain.SkillProfile;
import com.hireready.candidate.dto.CandidateRequest;
import com.hireready.candidate.dto.CandidateResponse;
import com.hireready.candidate.dto.SkillProfileResponse;
import com.hireready.candidate.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;
    private final InterviewClient interviewClient;

    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidate(
         @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody CandidateRequest request) {

        CandidateResponse response = candidateService.createCandidate(userId,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{candidateId}/skill-profile")
    public ResponseEntity<SkillProfileResponse> getSkillProfile(
            @PathVariable Long candidateId,
            @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.ok(
                candidateService.getSkillProfile(candidateId, userId)
        );
    }

    @GetMapping("/interview/summary")
    public ResponseEntity<?> getInterviewSummary(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam Long jobId) {

        return ResponseEntity.ok(
                interviewClient.fetchInterviewSummary(userId, jobId)
        );
    }
}
