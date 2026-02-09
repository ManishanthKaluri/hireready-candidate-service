package com.hireready.candidate.controller;

import com.hireready.candidate.ai.AiResumeAnalysisService;
import com.hireready.candidate.domain.SkillProfile;
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

    private final AiResumeAnalysisService aiResumeAnalysisService;

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
    public String extractText(@PathVariable Long candidateId) {
        
        return resumeService.extractResumeText(candidateId);
    }

       
    @PostMapping("/ai")
    public SkillProfile ai( @PathVariable Long candidateId) {
        
        String resumeText = resumeService.extractResumeText(candidateId);
        SkillProfile profile =
        aiResumeAnalysisService.extractSkillProfile(resumeText);
        System.out.println(profile);

        return profile;
    }


    
}
