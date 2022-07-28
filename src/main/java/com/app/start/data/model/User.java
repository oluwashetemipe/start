package com.app.start.data.model;

import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tableUser")
@AllArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private LocalDate dateCreated;
//    @Column
//    private String role;

    public User() {
    }
}
