package com.hireready.candidate.service;

import com.hireready.candidate.model.Candidate;
import com.hireready.candidate.model.Resume;
import com.hireready.candidate.ai.AiResumeAnalysisService;
import com.hireready.candidate.domain.SkillProfile;
import com.hireready.candidate.repository.CandidateRepository;
import com.hireready.candidate.repository.ResumeRepository;
import com.hireready.candidate.repository.SkillProfileRepository;
import com.hireready.candidate.resume.ResumeTextExtractor;
import com.hireready.candidate.resume.ResumeTextExtractorFactory;

import lombok.RequiredArgsConstructor;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CandidateAnalysisServiceImpl implements CandidateAnalysisService {

    private final CandidateRepository candidateRepository;
    private final ResumeRepository resumeRepository;
    private final ResumeTextExtractorFactory extractorFactory;
    private final AiResumeAnalysisService aiResumeAnalysisService;
    private final SkillProfileRepository skillProfileRepository;

    @Override
    @Transactional
    public void analyzeCandidateResume(Long candidateId) {

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Resume resume = resumeRepository.findByCandidateId(candidateId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        File resumeFile = new File(resume.getStoragePath());

        ResumeTextExtractor extractor =
                extractorFactory.getExtractor(resume.getFileType());

        String resumeText = extractor.extractText(resumeFile);

        SkillProfile skillProfile =
                aiResumeAnalysisService.extractSkillProfile(resumeText);

        skillProfile.setCandidate(candidate);

        skillProfileRepository.save(skillProfile);
    }
}