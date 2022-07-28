package com.app.start.service.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserRegistrationResponseDto {
    private Long id;
    private String userName;
    private String firstName;
    private LocalDate date;
}
