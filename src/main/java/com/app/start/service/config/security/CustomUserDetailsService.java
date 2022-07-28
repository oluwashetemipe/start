//package com.app.start.service.config.security;
//
//import com.app.start.data.model.User;
//import com.app.start.data.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUserName(username);
//        if (user.isEmpty()){
//            throw new UsernameNotFoundException("User not found");
//        }
//        User user1 = user.get();
//        return new CustomUserDetails(user1);
//    }
//}
