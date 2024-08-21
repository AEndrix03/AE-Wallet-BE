package com.aendrix.aewallet.dto.user;

import lombok.Data;

@Data
public class UserRegisterDto extends UserLoginDto {

    private String name;
    private String surname;

}
