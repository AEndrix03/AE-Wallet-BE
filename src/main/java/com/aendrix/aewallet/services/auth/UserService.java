package com.aendrix.aewallet.services.auth;

import com.aendrix.aewallet.dto.user.TokenDto;
import com.aendrix.aewallet.dto.user.UserDto;
import com.aendrix.aewallet.dto.user.UserLoginDto;
import com.aendrix.aewallet.dto.user.UserRegisterDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.coyote.BadRequestException;

public interface UserService {
    boolean existsUser(String mail);

    TokenDto loginUser(UserLoginDto loginDto) throws JsonProcessingException;

    TokenDto registerUser(UserRegisterDto registerDto) throws BadRequestException, JsonProcessingException;

    TokenDto refreshToken(String token) throws JsonProcessingException;

    UserDto getUserInfo(String token);
}
