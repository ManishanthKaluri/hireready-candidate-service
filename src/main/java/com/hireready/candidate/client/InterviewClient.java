package com.hireready.candidate.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class InterviewClient {

    private final RestTemplate restTemplate;

    @Value("${services.interview-service.base-url}")
    private String interviewServiceBaseUrl;

    public Map<String, Object> fetchInterviewSummary(Long candidateId, Long jobId) {

        String url = interviewServiceBaseUrl +
                "/api/interviews/candidate/" + candidateId + "/job/" + jobId + "/latest-summary";

        ResponseEntity<Map> response =
                restTemplate.getForEntity(url, Map.class);

        return response.getBody();
    }
}