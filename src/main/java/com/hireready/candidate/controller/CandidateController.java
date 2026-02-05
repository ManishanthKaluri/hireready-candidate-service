package com.hireready.candidate.controller;

import com.hireready.candidate.dto.CandidateRequest;
import com.hireready.candidate.dto.CandidateResponse;
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

    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidate(
            @Valid @RequestBody CandidateRequest request) {

        CandidateResponse response = candidateService.createCandidate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
