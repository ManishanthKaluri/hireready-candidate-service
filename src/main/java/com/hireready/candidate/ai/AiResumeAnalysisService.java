package com.hireready.candidate.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.hireready.candidate.domain.SkillProfile;
import com.hireready.candidate.ai.parser.SkillProfileParser;
import com.hireready.candidate.ai.validation.SkillProfileValidator;
@Service
@RequiredArgsConstructor
public class AiResumeAnalysisService {

    private final AiClient aiClient;
    private final ResumePromptBuilder promptBuilder;
    private final SkillProfileParser parser;
    private final SkillProfileValidator validator;

    public SkillProfile extractSkillProfile(String resumeText) {

        String prompt = promptBuilder.buildSkillExtractionPrompt(resumeText);

        String aiResponse = aiClient.call(prompt);

        SkillProfile parsed = parser.parse(aiResponse);

        return validator.validateAndNormalize(parsed);
    }
}

