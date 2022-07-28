package com.app.start.controller;


import com.app.start.data.repository.UserRepository;

import com.app.start.service.dto.UpdateUserRequestDto;
import com.app.start.service.dto.UserLoginDto;
import com.app.start.service.dto.UserRegistrationRequestDto;
import com.app.start.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")
public class AppController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/test")
    public String Registration() {
       return "Start";
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> Registration(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        try {
            //entry point
            return new ResponseEntity<>(userService.registerUser(userRegistrationRequestDto), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login-user")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto) {
        try {
            //entry point
            return new ResponseEntity<>(userService.loginUser(userLoginDto), HttpStatus.OK);
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
    @GetMapping("/list-all-users")
    public ResponseEntity<?> listAllUsers(@RequestParam(value = "page",defaultValue = "1") int page,
                                             @RequestParam(value = "size",defaultValue = "2") int size) {
        try {
            return new ResponseEntity<>(userService.findAllUsers(page,size), HttpStatus.OK);
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


//    @GetMapping("/user/find-by-date/{date}")
//    public ResponseEntity<?> findByDateCreated(@PathVariable("date")  LocalDate localDate){
//        try{
//            return new ResponseEntity<>(userService.findByDateCreated(localDate), HttpStatus.OK);
//        }catch (Exception exception){
//            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
    @GetMapping("/user/firstName/{firstName}")
    public ResponseEntity<?> findByFirstName(@PathVariable("firstName")  String firstName){
        try{
            return new ResponseEntity<>(userService.findByFirstName(firstName), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
