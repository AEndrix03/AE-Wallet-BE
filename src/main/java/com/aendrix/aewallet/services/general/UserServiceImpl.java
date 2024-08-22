package com.aendrix.aewallet.services.general;

import com.aendrix.aewallet.dto.user.UserDto;
import com.aendrix.aewallet.dto.user.UserLoginDto;
import com.aendrix.aewallet.dto.user.UserRegisterDto;
import com.aendrix.aewallet.entity.WltUser;
import com.aendrix.aewallet.repositories.UserRepository;
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
        WltUser wltUser = this.getUserByMail(registerDto.getMail());

        if (wltUser != null) {
            throw new BadRequestException("User already exists");
        }

        wltUser = new WltUser();
        try {
            String cryptoKey = this.cryptoService.generateKey();
            assert cryptoKey != null;
            wltUser.setName(this.cryptoService.encrypt(registerDto.getName(), cryptoKey));
            wltUser.setSurname(this.cryptoService.encrypt(registerDto.getSurname(), cryptoKey));
            wltUser.setMail(this.cryptoService.encrypt(registerDto.getMail(), cryptoKey));
        } catch (Exception e) {
            throw new InternalError("Error");
        }
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
            String cryptoKey = this.cryptoService.generateKey();
            assert cryptoKey != null;
            WltUser wltUser = this.userRepository.getUserByMail(this.jwtService.extractUsername(token.substring(7)));
            return UserDto.builder()
                    .id(wltUser.getId())
                    .name(this.cryptoService.decrypt(wltUser.getName(), cryptoKey))
                    .surname(this.cryptoService.decrypt(wltUser.getSurname(), cryptoKey))
                    .mail(this.cryptoService.decrypt(wltUser.getMail(), cryptoKey))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    private WltUser getUserByMail(String mail) {
        try {
            String cryptoKey = this.cryptoService.generateKey();
            assert cryptoKey != null;
            return this.userRepository.getUserByMail(this.cryptoService.encrypt(mail, cryptoKey));
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
        try {
            String cryptoKey = this.cryptoService.generateKey();
            assert cryptoKey != null;
            String mail = this.cryptoService.encrypt(loginDto.getMail(), cryptoKey);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            mail,
                            loginDto.getPassword()
                    )
            );
            return this.userRepository.getUserByMail(mail);
        } catch (Exception e) {
            throw new InternalError("Error");
        }
    }

}
