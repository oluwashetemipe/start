package com.app.start.service.userService;

import com.app.start.data.model.User;
import com.app.start.data.repository.UserRepository;
import com.app.start.exceptions.RegistrationException;
import com.app.start.exceptions.UserNotFoundException;
import com.app.start.service.dto.*;
import com.app.start.service.config.util.StringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;



    @Override
    public HttpStatus deleteByUserName(String userName) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user1 = user.get();
        Long id = user1.getId();
        userRepository.deleteById(id);
        return HttpStatus.OK;
    }


    @Override
    public UserResponseDto FindById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        UserResponseDto responseDto = new UserResponseDto();
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOptional.get();
        modelMapper.map(user,responseDto);
        return responseDto;
    }

    @Override
    public UpdateUserResponseDto editUser(UpdateUserRequestDto requestDto) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(requestDto.getUserName());
        if (userOptional.isEmpty()){
            throw new UserNotFoundException(("User not found"));
        }
        User user = userOptional.get();
        modelMapper.map(requestDto,user);
        userRepository.save(user);
        UpdateUserResponseDto userResponseDto = new UpdateUserResponseDto();
        modelMapper.map(requestDto,userResponseDto);
        return userResponseDto;
    }



    @Override
    public UserListDto findAllUsers(int page,int size) {
        Pageable pageable = PageRequest.of((page - 1), size);
        Page<User> users = userRepository.findAll(pageable);
        int totalSizeOfList = userRepository.findAll().size();
        List<User> userList = users.getContent();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User user : userList) {
            //loop through contents
            UserResponseDto userResponseDto = new UserResponseDto();
            //map contents to dto
            modelMapper.map(user, userResponseDto);
            //add dto to dto list
            userResponseDtoList.add(userResponseDto);
        }
//        create response object
        UserListDto userListDto = new UserListDto();
        userListDto.setResponseDtoList(userResponseDtoList);
        userListDto.setSizeOfList(totalSizeOfList);
        return userListDto;
    }

    @Override
    public UserResponseDto findByUsername(String userName) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        UserResponseDto userResponseDto = new UserResponseDto();
        if (userOptional.isEmpty()){
            throw new UserNotFoundException(("Username does not exist"));
        }
        User user = userOptional.get();
        modelMapper.map(user,userResponseDto);
        return userResponseDto;
    }
    @Transactional(readOnly = true)
    public UserResponseDto findByDateCreated(LocalDate date) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByDateCreated(date);
        UserResponseDto userResponseDto = new UserResponseDto();
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOptional.get();
        modelMapper.map(user,userResponseDto);
        return userResponseDto;
    }
    @Transactional(readOnly = true)
    public UserResponseDto findByFirstName(String firstName) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByFirstName(firstName);
        UserResponseDto userResponseDto = new UserResponseDto();
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOptional.get();
        modelMapper.map(user,userResponseDto);
        return userResponseDto;
    }



    @Override
    public UserRegistrationResponseDto registerUser(UserRegistrationRequestDto requestDto) throws RegistrationException {
        //check all fields
        checkAllParameters(requestDto);
        //check if username exists
        userWithUserNameExists(requestDto);
        //register a user
        User user = createUserFromDetails(requestDto);
        return generateRegistrationResponse(requestDto, user);
    }
    private User createUserFromDetails(UserRegistrationRequestDto userRegistrationRequestDto) {
        User user = new User();
        modelMapper.map(userRegistrationRequestDto, user);
        user.setDateCreated(LocalDate.now());
//        user.setRole("User");
        userRepository.save(user);
        return user;
    }

    private UserRegistrationResponseDto  generateRegistrationResponse(UserRegistrationRequestDto userRegistrationRequestDto, User user){
        UserRegistrationResponseDto  userRegistrationResponseDto = new UserRegistrationResponseDto();
        modelMapper.map(userRegistrationRequestDto, userRegistrationResponseDto);
        userRegistrationResponseDto.setId(user.getId());
        userRegistrationResponseDto.setDate(user.getDateCreated());
        return userRegistrationResponseDto;
    }

    private void userWithUserNameExists(UserRegistrationRequestDto userRegistrationRequestDto) throws  RegistrationException {

        Optional<User> findUserBy = userRepository.findByUserName(userRegistrationRequestDto.getUserName());
        if(findUserBy.isPresent()){
            throw new RegistrationException("User with this username already exists");
        }
    }
    private void checkAllParameters(UserRegistrationRequestDto userRegistrationRequestDto ) throws RegistrationException {

        if (StringUtil.isBlank(userRegistrationRequestDto.getFirstName())) {
            throw new RegistrationException("FirstName Cannot be empty");
        }
        if (StringUtil.isBlank(userRegistrationRequestDto.getLastName())) {
            throw new RegistrationException("LastName Cannot be empty");
        }
        if (StringUtil.isBlank(userRegistrationRequestDto.getUserName())){
            throw new RegistrationException("UserName cannot be empty");
        }
        if (StringUtil.isBlank(userRegistrationRequestDto.getPassword())){
            throw new RegistrationException("Password cannot be empty");
        }

    }
}
