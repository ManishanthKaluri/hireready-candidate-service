package com.hireready.candidate.controller;

import com.hireready.candidate.resume.ResumeTextExtractorFactory;
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

    private final ResumeTextExtractorFactory resumeTextExtractorFactory;

    private final ResumeService resumeService;

    // ResumeController(ResumeTextExtractorFactory resumeTextExtractorFactory) {
    //     this.resumeTextExtractorFactory = resumeTextExtractorFactory;
    // }

    @PostMapping
    public ResponseEntity<String> uploadResume(
            @PathVariable Long candidateId,
            @RequestParam("file") MultipartFile file) {

        resumeService.uploadResume(candidateId, file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Resume uploaded successfully");
    }

    @PostMapping("/extract")
    public String postMethodName( @PathVariable Long candidateId) {
       
        
        return resumeService.extractResumeText(candidateId);
    }
    
}
