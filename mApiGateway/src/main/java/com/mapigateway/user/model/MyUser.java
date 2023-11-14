package com.mapigateway.user.model;


import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {
    String _id;
    String email;
    String userName;
    String login;
    String password;
    String firstName;
    String lastName;
    String phoneNumber;
    String roles;
    Double balance;
}
