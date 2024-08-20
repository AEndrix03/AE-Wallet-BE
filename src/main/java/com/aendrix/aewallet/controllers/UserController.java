package com.aendrix.aewallet.controllers;

import com.aendrix.aewallet.dto.user.UserLoginDto;
import com.aendrix.aewallet.dto.user.UserRegisterDto;
import com.aendrix.aewallet.services.general.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final String ROOT = "users";

    @Autowired
    private UserService userService;

    @GetMapping(ROOT)
    public boolean existsUser(@RequestParam(required = true) String mail) {
        return userService.existsUser(mail);
    }

    @PostMapping(ROOT + "/login")
    public Long loginUser(@RequestBody UserLoginDto loginDto) {
        return userService.loginUser(loginDto);
    }

    @PostMapping(ROOT + "/register")
    public Long registerUser(@RequestBody UserRegisterDto registerDto) {
        return userService.registerUser(registerDto);
    }

}
