package it.aredegalli.wallet.service.user;

import it.aredegalli.wallet.client.PraetorClient;
import it.aredegalli.wallet.dto.user.UserDto;
import it.aredegalli.wallet.entity.user.User;
import it.aredegalli.wallet.repository.user.UserRepository;
import it.aredegalli.wallet.service.user.provider.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${praetor.applicationName}")
    private String praetorAppName;

    private final UserRepository userRepository;
    private final PraetorClient praetorClient;
    private final UserProvider userProvider;

    @Override
    public Boolean hello(String token, String authenticator) {
        UserDto user = this.praetorClient.validateToken(token.substring(7), this.praetorAppName, authenticator, token);
        UUID userId = user.getId();

        this.userProvider.setUser(user);

        Boolean exists = this.userRepository.existsById(userId);
        if (!exists) {
            this.userRepository.save(User.builder().id(userId).build());
        }

        return exists;
    }

}
