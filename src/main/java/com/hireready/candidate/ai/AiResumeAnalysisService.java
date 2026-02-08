package com.hireready.candidate.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiResumeAnalysisService {

    private final AiClient aiClient;
    private final ResumePromptBuilder promptBuilder;

    public String extractSkills(String resumeText) {

        String prompt = promptBuilder.buildSkillExtractionPrompt(resumeText);

        return aiClient.call(prompt);
    }
}
