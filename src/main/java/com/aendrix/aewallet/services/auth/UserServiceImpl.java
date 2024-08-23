package com.aendrix.aewallet.services.auth;

import com.aendrix.aewallet.dto.user.UserDto;
import com.aendrix.aewallet.dto.user.UserLoginDto;
import com.aendrix.aewallet.dto.user.UserRegisterDto;
import com.aendrix.aewallet.entity.WltUser;
import com.aendrix.aewallet.repositories.auth.UserRepository;
import com.aendrix.aewallet.services.security.AESCryptoService;
import com.aendrix.aewallet.services.security.JwtService;
import org.apache.coyote.BadRequestException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AESCryptoService cryptoService;

    @Override
    public boolean existsUser(String mail) {
        return this.userRepository.getUserByMail(mail) != null;
    }

    @Override
    public String loginUser(UserLoginDto loginDto) {
        return this.jwtService.generateToken(this.authenticate(loginDto));
    }

    @Override
    public String registerUser(UserRegisterDto registerDto) throws BadRequestException {
        WltUser wltUser = this.userRepository.getUserByMail(registerDto.getMail());

        if (wltUser != null) {
            throw new BadRequestException("User already exists");
        }

        wltUser = new WltUser();
        String cryptoKey = this.cryptoService.getKeyFromDockerSecret();
        assert cryptoKey != null;
        try {
            wltUser.setName(this.cryptoService.encrypt(registerDto.getName(), cryptoKey));
            wltUser.setSurname(this.cryptoService.encrypt(registerDto.getSurname(), cryptoKey));
        } catch (Exception e) {
            throw new InternalError("Error");
        }
        wltUser.setMail(registerDto.getMail());
        wltUser.setPassword(hashPassword(registerDto.getPassword()));
        this.updateLastLogin(wltUser);

        return this.jwtService.generateToken(this.authenticate(registerDto));
    }

    @Override
    public String refreshToken(String token) {
        return this.jwtService.generateToken(this.userDetailsService.loadUserByUsername(this.jwtService.extractUsername(token.substring(7))));
    }

    @Override
    public UserDto getUserInfo(String token) {
        try {
            String cryptoKey = this.cryptoService.getKeyFromDockerSecret();
            assert cryptoKey != null;
            WltUser wltUser = this.userRepository.getUserByMail(this.jwtService.extractUsername(token.substring(7)));
            return UserDto.builder()
                    .id(wltUser.getId())
                    .name(this.cryptoService.decrypt(wltUser.getName(), cryptoKey))
                    .surname(this.cryptoService.decrypt(wltUser.getSurname(), cryptoKey))
                    .mail(wltUser.getMail())
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    private WltUser updateLastLogin(WltUser wltUser) {
        wltUser.setLastlogin(LocalDateTime.now());
        return this.userRepository.save(wltUser);
    }

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
    }

    private WltUser authenticate(UserLoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getMail(),
                        loginDto.getPassword()
                )
        );

        return this.userRepository.getUserByMail(loginDto.getMail());
    }

}
