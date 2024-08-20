package com.aendrix.aewallet.dto.user;

import lombok.Data;

@Data
public class UserRegisterDto {

    private String name;
    private String surname;
    private String mail;
    private String password;

}
