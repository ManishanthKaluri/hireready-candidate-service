package com.hireready.candidate.service;

import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {

    void uploadResume(Long candidateId, MultipartFile file);
}

