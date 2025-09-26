package it.aredegalli.wallet.service.user;

import it.aredegalli.wallet.entity.user.User;
import it.aredegalli.wallet.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Boolean hello(UUID userId) {
        Boolean exists = this.userRepository.existsById(userId);
        this.userRepository.save(User.builder().id(userId).build());
        return exists;
    }

}
