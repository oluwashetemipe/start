package com.app.start.service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class UserListDto {
    public List<UserResponseDto> responseDtoList = new ArrayList<>();

    public UserListDto() {
        this.responseDtoList = responseDtoList;
    }

    public List<UserResponseDto> getResponseDtoList() {
        return responseDtoList;
    }
}
