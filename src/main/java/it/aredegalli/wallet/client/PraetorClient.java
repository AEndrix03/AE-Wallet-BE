package it.aredegalli.wallet.client;

import it.aredegalli.wallet.dto.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "praetor", url = "${praetor.url}")
public interface PraetorClient {

    @GetMapping(value = "/user/validate")
    UserDto validateToken(@RequestParam String token,
                          @RequestParam String applicationName,
                          @RequestParam String authenticatorName,
                          @RequestHeader("Authorization") String authorizationHeader);

}
