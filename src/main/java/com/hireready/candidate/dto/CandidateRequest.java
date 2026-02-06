package com.hireready.candidate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateRequest {
    // @NotNull
    // private Long userId;

    @NotBlank
    private String fullName;

    @Email
    @NotBlank
    private String email;
    private String phone;


}
