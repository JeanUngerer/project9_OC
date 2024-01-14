package com.mapigateway.user.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {

    @Id
    String _id;
    @Indexed(unique = true)
    String email;
    @Indexed(unique = true)
    String userName;
    String login;
    String password;
    String firstName;
    String lastName;
    String phoneNumber;
    String roles;
}
