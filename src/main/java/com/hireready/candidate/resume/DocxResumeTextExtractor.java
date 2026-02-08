package com.hireready.candidate.resume;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class DocxResumeTextExtractor implements ResumeTextExtractor {

    @Override
    public String extractText(File resumeFile) {
        try (FileInputStream fis = new FileInputStream(resumeFile);
             XWPFDocument document = new XWPFDocument(fis);
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {

            return extractor.getText();
        } catch (IOException e) {
            throw new RuntimeException("Failed to extract text from DOCX", e);
        }
    }
}
