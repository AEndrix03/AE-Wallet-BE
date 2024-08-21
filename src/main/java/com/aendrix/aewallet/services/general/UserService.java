package com.aendrix.aewallet.services.general;

import com.aendrix.aewallet.dto.user.UserLoginDto;
import com.aendrix.aewallet.dto.user.UserRegisterDto;
import org.apache.coyote.BadRequestException;

public interface UserService {
    boolean existsUser(String mail);

    String loginUser(UserLoginDto loginDto);

    String registerUser(UserRegisterDto registerDto) throws BadRequestException;

    String refreshToken(String token);
}
