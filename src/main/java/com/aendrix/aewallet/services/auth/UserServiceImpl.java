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
    public TokenDto loginUser(UserLoginDto loginDto) {
        return TokenDto.builder().token(this.jwtService.generateToken(this.authenticate(loginDto))).build();
    }

    @Override
    public TokenDto registerUser(UserRegisterDto registerDto) throws BadRequestException {
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
    public TokenDto refreshToken(String token) {
        if (this.jwtService.isTokenExpired(token.substring(7))) {
            return null;
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(this.jwtService.extractUsername(token.substring(7)));

        if (userDetails == null) {
            return null;
        }

        return TokenDto.builder().token(this.jwtService.generateToken(this.userRepository.getUserByMail(userDetails.getUsername()))).build();
    }

    @Override
    public UserDto getUserInfo(String token) {
        try {
            WltUser wltUser = this.userRepository.getUserByMail(this.jwtService.extractUsername(token.substring(7)));
            decryptUser(wltUser);
            UserDto user = UserDto.builder()
                    .id(wltUser.getId())
                    .name(wltUser.getName())
                    .surname(wltUser.getSurname())
                    .mail(wltUser.getMail())
                    .build();
            this.userProvider.setUserDto(user);
            return user;
        } catch (Exception e) {
            return null;
        }
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

        return this.userRepository.getUserByMail(loginDto.getMail());
    }

    /*
    private void saveUserSession(WltUser authUser) {
        if (authUser == null) {
            return null;
        }

        WltUser decryptedUser = new WltUser(authUser);
        decryptUser(decryptedUser);
        this.userProvider.setUserDto(decryptedUser.toDto());

        return TokenDto.builder().token(this.jwtService.generateToken(authUser)).build();
    }
    */

    private void decryptUser(WltUser authUser) {
        String cryptoKey = this.cryptoService.getKeyFromDockerSecret();
        try {
            authUser.setName(this.cryptoService.decrypt(authUser.getName(), cryptoKey));
            authUser.setSurname(this.cryptoService.decrypt(authUser.getSurname(), cryptoKey));
        } catch (Exception e) {
            throw new InternalError("Error");
        }
    }

    private void encryptUser(WltUser authUser) {
        String cryptoKey = this.cryptoService.getKeyFromDockerSecret();
        try {
            authUser.setName(this.cryptoService.encrypt(authUser.getName(), cryptoKey));
            authUser.setSurname(this.cryptoService.encrypt(authUser.getSurname(), cryptoKey));
        } catch (Exception e) {
            throw new InternalError("Error");
        }
    }

}
