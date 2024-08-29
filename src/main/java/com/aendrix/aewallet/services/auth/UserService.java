package com.aendrix.aewallet.services.auth;

import com.aendrix.aewallet.dto.user.TokenDto;
import com.aendrix.aewallet.dto.user.UserDto;
import com.aendrix.aewallet.dto.user.UserLoginDto;
import com.aendrix.aewallet.dto.user.UserRegisterDto;
import org.apache.coyote.BadRequestException;

public interface UserService {
    boolean existsUser(String mail);

    TokenDto loginUser(UserLoginDto loginDto);

    TokenDto registerUser(UserRegisterDto registerDto) throws BadRequestException;

    TokenDto refreshToken(String token);

    UserDto getUserInfo(String token);
}
