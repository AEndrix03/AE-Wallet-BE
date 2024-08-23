package com.aendrix.aewallet.repositories;

import com.aendrix.aewallet.entity.WltUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<WltUser, Long> {

    WltUser getUserByMail(String mail);
    
}

