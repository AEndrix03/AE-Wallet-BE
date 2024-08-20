package com.aendrix.aewallet.repositories;

import com.aendrix.aewallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByMail(String mail);

    User getUserByMailAndPassword(String mail, String password);

}

