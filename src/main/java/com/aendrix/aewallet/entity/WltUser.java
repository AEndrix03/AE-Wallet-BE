package com.aendrix.aewallet.entity;

import com.aendrix.aewallet.dto.user.UserDto;
import com.aendrix.aewallet.utils.DtoMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "wlt_user")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class WltUser implements UserDetails, DtoMapper<UserDto> {

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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.getMail();
    }

    @Override
    public UserDto toDto() {
        return UserDto.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .mail(mail)
                .build();
    }
}
