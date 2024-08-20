package com.aendrix.aewallet.services.general;

import com.aendrix.aewallet.dto.user.UserDto;
import com.aendrix.aewallet.dto.user.UserLoginDto;
import com.aendrix.aewallet.dto.user.UserRegisterDto;
import org.apache.coyote.BadRequestException;

public interface UserService {
    boolean existsUser(String mail);

    Long loginUser(UserLoginDto loginDto);

    Long registerUser(UserRegisterDto registerDto);
}
