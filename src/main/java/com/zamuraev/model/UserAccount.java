package com.zamuraev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name="user_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;
    private int age;
    private String email;
    private String gender;
    private String phoneNumber;
    private String city;
    private String country;
    @OneToOne(mappedBy = "userAccount")
    private Interest interest;
}
