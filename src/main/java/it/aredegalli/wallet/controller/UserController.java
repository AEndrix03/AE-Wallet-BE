package it.aredegalli.wallet.controller;

import it.aredegalli.wallet.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("hello")
    public Boolean getAllTransactionTypes(@RequestParam UUID id) {
        return this.userService.hello(id);
    }

}
