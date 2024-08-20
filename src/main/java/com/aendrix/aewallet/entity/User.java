package com.aendrix.aewallet.entity;

import com.aendrix.aewallet.dto.user.UserDto;
import com.aendrix.aewallet.utils.DtoMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements DtoMapper<UserDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    private String password;

    @Column(name = "lastlogin")
    private LocalDateTime lastlogin;

    @Override
    public UserDto toDto() {
        return UserDto.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .mail(mail)
                .password(password)
                .lastlogin(lastlogin)
                .build();
    }
}
