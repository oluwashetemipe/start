package com.app.start.service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class UserListDto {
    private int sizeOfList;
    public List<UserResponseDto> responseDtoList = new ArrayList<>();



}
