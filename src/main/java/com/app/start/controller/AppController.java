package com.app.start.controller;


import com.app.start.data.repository.UserRepository;

import com.app.start.service.dto.UpdateUserRequestDto;
import com.app.start.service.dto.UserRegistrationRequestDto;
import com.app.start.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AppController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<?> Registration(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        try {
            //entry point
            return new ResponseEntity<>(userService.registerUser(userRegistrationRequestDto), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fetch-user-by-username/{userName}")
    public ResponseEntity<?> fetchAUserByUserName(@PathVariable("userName") String userName) {
        try {
            return new ResponseEntity<>(userService.findByUsername(userName), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
    @PostMapping("/edit-user")
    public ResponseEntity<?> EditUser(@RequestBody UpdateUserRequestDto updateUserRequestDto) {
        try {
            //entry point
            return new ResponseEntity<>(userService.editUser(updateUserRequestDto), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-user/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable("userName")  String userName){
        try{
            return new ResponseEntity<>(userService.deleteByUserName(userName), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




}
