package com.mapigateway.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    String _id;
    String email;
    String userName;
    String login;
    String password;
    String firstName;
    String lastName;
    String phoneNumber;
    String roles;
}
