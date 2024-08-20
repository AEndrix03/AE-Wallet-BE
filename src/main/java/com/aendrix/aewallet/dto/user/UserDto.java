package com.aendrix.aewallet.dto.user;

import com.aendrix.aewallet.entity.User;
import com.aendrix.aewallet.utils.EntityMapper;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class UserDto implements EntityMapper<User> {

    private long id;
    private String name;
    private String surname;
    private String mail;
    private String password;
    private LocalDateTime lastlogin;

    @Override
    public User toEntity() {
        return User.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .mail(mail)
                .password(password)
                .lastlogin(lastlogin)
                .build();
    }
}
