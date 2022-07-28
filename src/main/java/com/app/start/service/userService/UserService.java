package com.app.start.service.userService;

import com.app.start.exceptions.GeneralServiceException;
import com.app.start.exceptions.IncorrectPasswordException;
import com.app.start.exceptions.RegistrationException;
import com.app.start.exceptions.UserNotFoundException;
import com.app.start.service.dto.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public interface UserService {




    UserResponseDto FindById(Long id) throws UserNotFoundException;

    UpdateUserResponseDto editUser(UpdateUserRequestDto requestDto) throws GeneralServiceException, UserNotFoundException;

    UserListDto findAllUsers(int page,int size);

    HttpStatus deleteByUserName(String userName) throws GeneralServiceException,UserNotFoundException;

    UserResponseDto findByFirstName(String firstName) throws GeneralServiceException,UserNotFoundException;

    UserResponseDto findByDateCreated(LocalDate date) throws UserNotFoundException,GeneralServiceException;

    UserResponseDto findByUsername(String userName) throws GeneralServiceException, UserNotFoundException;

    UserRegistrationResponseDto registerUser(UserRegistrationRequestDto requestDto) throws RegistrationException;

    UserLoginResponseDto loginUser(UserLoginDto userLoginDto) throws RegistrationException, UserNotFoundException, IncorrectPasswordException;
}
