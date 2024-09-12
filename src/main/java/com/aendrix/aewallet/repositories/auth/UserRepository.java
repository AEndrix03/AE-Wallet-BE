package com.aendrix.aewallet.repositories.auth;

import com.aendrix.aewallet.entity.WltUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<WltUser, Long> {

    WltUser getUserByMail(String mail);

}

