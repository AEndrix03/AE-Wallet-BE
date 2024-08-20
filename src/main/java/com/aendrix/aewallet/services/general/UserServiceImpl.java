package com.aendrix.aewallet.services.general;

import com.aendrix.aewallet.dto.user.UserLoginDto;
import com.aendrix.aewallet.dto.user.UserRegisterDto;
import com.aendrix.aewallet.entity.User;
import com.aendrix.aewallet.exceptions.DetailedBadRequestException;
import com.aendrix.aewallet.repositories.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository scrzAppRepository;

    @Override
    public boolean existsUser(String mail) {
        return this.scrzAppRepository.getUserByMail(mail) != null;
    }

    @Override
    public Long loginUser(UserLoginDto loginDto) {
        //Immaginare che la pwd sia da crittare
        User user = this.scrzAppRepository.getUserByMailAndPassword(loginDto.getMail(), loginDto.getPassword());

        if (user == null) {
            //throw new DetailedBadRequestException();
            return -1L; //da gestire
        }

        return this.updateLastLogin(user).getId();
    }

    @Override
    public Long registerUser(UserRegisterDto registerDto) {
        //Immaginare che la pwd sia da crittare
        User user = this.scrzAppRepository.getUserByMail(registerDto.getMail());

        if (user != null) {
            //throw new DetailedBadRequestException();
            return -1L; //da gestire
        }

        user = new User();
        user.setName(registerDto.getName());
        user.setSurname(registerDto.getSurname());
        user.setMail(registerDto.getMail());
        user.setPassword(registerDto.getPassword()); //da crittare

        return this.updateLastLogin(user).getId();
    }

    private User updateLastLogin(User user) {
        user.setLastlogin(LocalDateTime.now());
        return this.scrzAppRepository.save(user);
    }
}
