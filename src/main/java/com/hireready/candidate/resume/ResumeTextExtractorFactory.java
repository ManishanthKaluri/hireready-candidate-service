package com.hireready.candidate.resume;

import org.springframework.stereotype.Component;

@Component
public class ResumeTextExtractorFactory {

    private final PdfResumeTextExtractor pdfExtractor;
    private final DocxResumeTextExtractor docxExtractor;

    public ResumeTextExtractorFactory(
            PdfResumeTextExtractor pdfExtractor,
            DocxResumeTextExtractor docxExtractor) {
        this.pdfExtractor = pdfExtractor;
        this.docxExtractor = docxExtractor;
    }

    public ResumeTextExtractor getExtractor(String contentType) {

        if ("application/pdf".equals(contentType)) {
            return pdfExtractor;
        }

        if ("application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                .equals(contentType)) {
            return docxExtractor;
        }

        throw new IllegalArgumentException("Unsupported resume file type");
    }
}
