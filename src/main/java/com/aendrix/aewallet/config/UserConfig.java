package com.aendrix.aewallet.config;

import com.aendrix.aewallet.dto.user.UserDto;
import com.aendrix.aewallet.services.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
public class UserConfig {

    @Autowired
    private UserProvider userProvider;

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserDto getUserDto() {
        return this.userProvider.getUserDto();
    }


}
