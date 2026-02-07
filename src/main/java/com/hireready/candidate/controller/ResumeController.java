package com.hireready.candidate.controller;

import com.hireready.candidate.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/candidates/{candidateId}/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<String> uploadResume(
            @PathVariable Long candidateId,
            @RequestParam("file") MultipartFile file) {

        resumeService.uploadResume(candidateId, file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Resume uploaded successfully");
    }
}
