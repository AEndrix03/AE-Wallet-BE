package com.aendrix.aewallet.controllers;

import com.aendrix.aewallet.dto.user.UserLoginDto;
import com.aendrix.aewallet.dto.user.UserRegisterDto;
import com.aendrix.aewallet.services.general.UserService;
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
    public String loginUser(@RequestBody UserLoginDto loginDto) throws BadRequestException {
        return userService.loginUser(loginDto);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegisterDto registerDto) throws BadRequestException {
        return userService.registerUser(registerDto);
    }

    @PostMapping("/logout")
    public void logoutUser(@RequestParam(required = true) Long id) {
        //userService.logoutUser(id);
    }

}
