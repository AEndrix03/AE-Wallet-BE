package it.aredegalli.wallet.repository.user;

import it.aredegalli.wallet.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}

