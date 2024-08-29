package com.aendrix.aewallet.controllers;

import com.aendrix.aewallet.dto.user.TokenDto;
import com.aendrix.aewallet.dto.user.UserDto;
import com.aendrix.aewallet.dto.user.UserLoginDto;
import com.aendrix.aewallet.dto.user.UserRegisterDto;
import com.aendrix.aewallet.services.auth.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public boolean existsUser(@RequestParam(required = true) String mail) {
        return userService.existsUser(mail);
    }

    @PostMapping("/login")
    public TokenDto loginUser(@RequestBody UserLoginDto loginDto) {
        return userService.loginUser(loginDto);
    }

    @PostMapping("/register")
    public TokenDto registerUser(@RequestBody UserRegisterDto registerDto) throws BadRequestException {
        return userService.registerUser(registerDto);
    }

    @GetMapping("/user-info")
    public UserDto getUserInfo(@RequestHeader("Authorization") String token) {
        return userService.getUserInfo(token);
    }

}
