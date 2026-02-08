package com.hireready.candidate.ai;

import org.springframework.stereotype.Component;

@Component
public class ResumePromptBuilder {

    public String buildSkillExtractionPrompt(String resumeText) {

        return """
You are a resume parser.

Extract ONLY the following information from the resume text below.

Return STRICT JSON in this exact schema:

{
  "primarySkills": string[],
  "secondarySkills": string[],
  "yearsOfExperience": number,
  "roles": string[],
  "seniority": "JUNIOR" | "MID" | "SENIOR",
  "industries": string[]
}

Rules:
- Do NOT include explanations
- Do NOT include markdown
- Do NOT guess years if not clearly stated (use null)
- Normalize skill names (e.g., "Spring Boot")
- If a field is missing, return an empty array or null

Resume Text:
\"\"\"%s\"\"\"
""".formatted(resumeText);
    }
}
