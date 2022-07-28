package com.app.start.service.userService;

import com.app.start.exceptions.GeneralServiceException;
import com.app.start.exceptions.RegistrationException;
import com.app.start.exceptions.UserNotFoundException;
import com.app.start.service.dto.*;
import org.springframework.http.HttpStatus;

public interface UserService {




    UserResponseDto FindById(Long id) throws UserNotFoundException;

    UpdateUserResponseDto editUser(UpdateUserRequestDto requestDto) throws GeneralServiceException, UserNotFoundException;

//    UserListDto findAllUsers();

    HttpStatus deleteByUserName(String userName) throws GeneralServiceException,UserNotFoundException;

//    UserResponseDto findByFirstName(String name) throws GeneralServiceException;
//
//    List<UserResponseDto> findByDateCreated(Date date) throws GeneralServiceException;

    UserResponseDto findByUsername(String userName) throws GeneralServiceException, UserNotFoundException;

    UserRegistrationResponseDto registerUser(UserRegistrationRequestDto requestDto) throws RegistrationException;

}
