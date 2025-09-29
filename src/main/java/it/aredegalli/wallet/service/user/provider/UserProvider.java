package it.aredegalli.wallet.service.user.provider;

import it.aredegalli.wallet.dto.user.UserDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProvider {

    @Getter
    @Setter
    private UserDto user;

}
