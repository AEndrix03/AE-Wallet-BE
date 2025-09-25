package it.aredegalli.wallet.repository.user;

import it.aredegalli.wallet.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

