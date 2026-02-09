package com.hireready.candidate.ai.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hireready.candidate.domain.SkillProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkillProfileParser {

    private final ObjectMapper objectMapper;

    public SkillProfile parse(String aiJson) {

        try {
            return objectMapper.readValue(aiJson, SkillProfile.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI skill profile", e);
        }
    }
}
