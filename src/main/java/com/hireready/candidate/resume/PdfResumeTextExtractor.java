package com.hireready.candidate.resume;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class PdfResumeTextExtractor implements ResumeTextExtractor {

    @Override
    public String extractText(File resumeFile) {
        try (PDDocument document = PDDocument.load(resumeFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            throw new RuntimeException("Failed to extract text from PDF", e);
        }
    }
}
