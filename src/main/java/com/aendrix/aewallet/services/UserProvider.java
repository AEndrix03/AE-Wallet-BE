package com.aendrix.aewallet.services;

import com.aendrix.aewallet.dto.user.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserProvider {

    private final ThreadLocal<UserDto> userDto = new ThreadLocal<>();

    public void setUserDto(UserDto userDto) {
        this.userDto.set(userDto);
    }

    public UserDto getUserDto() {
        return userDto.get();
    }

    public void clear() {
        userDto.remove();
    }

}
