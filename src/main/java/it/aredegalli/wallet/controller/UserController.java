package it.aredegalli.wallet.controller;

import it.aredegalli.wallet.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("hello")
    public Boolean hello(@RequestHeader String authorization, @RequestParam String authenticator) {
        return this.userService.hello(authorization, authenticator);
    }

}
