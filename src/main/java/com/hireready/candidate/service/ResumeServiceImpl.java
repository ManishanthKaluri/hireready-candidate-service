package com.hireready.candidate.service;

import com.hireready.candidate.model.Resume;
import com.hireready.candidate.repository.ResumeRepository;
import com.hireready.candidate.resume.ResumeTextExtractor;
import com.hireready.candidate.resume.ResumeTextExtractorFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    private final ResumeTextExtractorFactory extractorFactory;

    @Value("${resume.storage.path}")
    private String storagePath;

    @Override
    public void uploadResume(Long candidateId, MultipartFile file) {

        validateFile(file);

        try {
            Files.createDirectories(Paths.get(storagePath));

            String storedFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadDir = Paths.get(storagePath).toAbsolutePath();
            Files.createDirectories(uploadDir);

            Path filePath = uploadDir.resolve(storedFileName);
            file.transferTo(filePath.toFile());

            Resume resume = Resume.builder()
                    .candidateId(candidateId)
                    .originalFileName(file.getOriginalFilename())
                    .storedFileName(storedFileName)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .storagePath(filePath.toString())
                    .build();

            resumeRepository.save(resume);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload resume", e);
        }
    }

    private void validateFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Resume file is empty");
        }

        String contentType = file.getContentType();
        if (!"application/pdf".equals(contentType)
                && !"application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(contentType)) {
            throw new IllegalArgumentException("Only PDF and DOCX files are allowed");
        }
    }

    @Override
    public String extractResumeText(Long candidateId) {

        Resume resume = resumeRepository.findByCandidateId(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Resume not found"));

        File resumeFile = new File(resume.getStoragePath());

        ResumeTextExtractor extractor =
                extractorFactory.getExtractor(resume.getFileType());

        return extractor.extractText(resumeFile);
    }

}
