package com.app.start.data.repository;


import com.app.start.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
//    List<User> findByName(String name);
//    List<User> findByDateCreated(Date date);
}
