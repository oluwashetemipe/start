package com.app.start.service.dto;

import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
}
