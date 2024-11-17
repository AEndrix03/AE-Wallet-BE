package com.aendrix.aewallet.services.auth;

import com.aendrix.aewallet.dto.user.TokenDto;
import com.aendrix.aewallet.dto.user.UserDto;
import com.aendrix.aewallet.dto.user.UserLoginDto;
import com.aendrix.aewallet.dto.user.UserRegisterDto;
import com.aendrix.aewallet.entity.WltUser;
import com.aendrix.aewallet.repositories.auth.UserRepository;
import com.aendrix.aewallet.services.UserProvider;
import com.aendrix.aewallet.services.security.AESCryptoService;
import com.aendrix.aewallet.services.security.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.coyote.BadRequestException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private UserProvider userProvider;

    @Override
    public boolean existsUser(String mail) {
        return this.userRepository.getUserByMail(mail) != null;
    }

    @Override
    public TokenDto loginUser(UserLoginDto loginDto) throws JsonProcessingException {
        return TokenDto.builder().token(this.jwtService.generateToken(this.authenticate(loginDto))).build();
    }

    @Override
    public TokenDto registerUser(UserRegisterDto registerDto) throws BadRequestException, JsonProcessingException {
        WltUser wltUser = this.userRepository.getUserByMail(registerDto.getMail());

        if (wltUser != null) {
            throw new BadRequestException("User already exists");
        }

        wltUser = new WltUser();
        encryptUser(wltUser);
        wltUser.setMail(registerDto.getMail());
        wltUser.setPassword(hashPassword(registerDto.getPassword()));
        this.updateLastLogin(wltUser);

        return TokenDto.builder().token(this.jwtService.generateToken(this.authenticate(registerDto))).build();
    }

    @Override
    public TokenDto refreshToken(String token) throws JsonProcessingException {
        if (this.jwtService.isTokenExpired(token.substring(7))) {
            return null;
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(this.jwtService.extractUsername(token.substring(7)));

        if (userDetails == null) {
            return null;
        }
        WltUser user = this.userRepository.getUserByMail(userDetails.getUsername());
        decryptUser(user);
        return TokenDto.builder().token(this.jwtService.generateToken(user)).build();
    }

    @Override
    public UserDto getUserInfo(String token) {
        return this.userProvider.getUserDto();
    }

    private void updateLastLogin(WltUser wltUser) {
        wltUser.setLastlogin(LocalDateTime.now());
        this.userRepository.save(wltUser);
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

        WltUser user = this.userRepository.getUserByMail(loginDto.getMail());
        decryptUser(user);
        return user;
    }

    private void decryptUser(WltUser authUser) {
        String cryptoKey = this.cryptoService.getKey();
        try {
            authUser.setName(this.cryptoService.decrypt(authUser.getName(), cryptoKey));
            authUser.setSurname(this.cryptoService.decrypt(authUser.getSurname(), cryptoKey));
        } catch (Exception e) {
            throw new InternalError("Error");
        }
    }

    private void encryptUser(WltUser authUser) {
        String cryptoKey = this.cryptoService.getKey();
        try {
            authUser.setName(this.cryptoService.encrypt(authUser.getName(), cryptoKey));
            authUser.setSurname(this.cryptoService.encrypt(authUser.getSurname(), cryptoKey));
        } catch (Exception e) {
            throw new InternalError("Error");
        }
    }

}
