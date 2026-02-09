package com.hireready.candidate.ai.validation;

import com.hireready.candidate.domain.SkillProfile;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SkillProfileValidator {

    public SkillProfile validateAndNormalize(SkillProfile profile) {

        if (profile == null) {
            throw new IllegalArgumentException("Skill profile is null");
        }

        profile.setPrimarySkills(normalize(profile.getPrimarySkills()));
        profile.setSecondarySkills(normalize(profile.getSecondarySkills()));
        profile.setRoles(normalize(profile.getRoles()));
        profile.setIndustries(normalize(profile.getIndustries()));

        if (profile.getYearsOfExperience() != null) {
            if (profile.getYearsOfExperience() < 0 || profile.getYearsOfExperience() > 40) {
                throw new IllegalArgumentException("Invalid years of experience");
            }
        }

        if (profile.getSeniority() == null) {
            throw new IllegalArgumentException("Seniority must not be null");
        }

        return profile;
    }

    private List<String> normalize(List<String> values) {
        if (values == null) {
            return Collections.emptyList();
        }

        return values.stream()
                .map(String::trim)
                .map(this::capitalize)
                .distinct()
                .collect(Collectors.toList());
    }

    private String capitalize(String value) {
        if (value.isEmpty()) return value;
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}
