package com.aendrix.aewallet.controllers;

import com.aendrix.aewallet.dto.user.TokenDto;
import com.aendrix.aewallet.services.auth.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public TokenDto refreshToken(@RequestHeader("Authorization") String authHeader) throws JsonProcessingException {
        return this.userService.refreshToken(authHeader);
    }

}
