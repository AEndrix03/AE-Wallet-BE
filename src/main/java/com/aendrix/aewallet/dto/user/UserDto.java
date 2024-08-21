package com.aendrix.aewallet.dto.user;

import com.aendrix.aewallet.entity.WltUser;
import com.aendrix.aewallet.utils.EntityMapper;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UserDto implements EntityMapper<WltUser> {

    private long id;
    private String name;
    private String surname;
    private String mail;

    @Override
    public WltUser toEntity() {
        return WltUser.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .mail(mail)
                .build();
    }
}
