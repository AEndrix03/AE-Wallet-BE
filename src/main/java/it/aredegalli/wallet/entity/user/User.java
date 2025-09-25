package it.aredegalli.wallet.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @ColumnDefault("now()")
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @ColumnDefault("now()")
    @Column(name = "update_date", nullable = false)
    private Instant updateDate;

    @Column(name = "creation_user")
    private UUID creationUser;

    @Column(name = "update_user")
    private UUID updateUser;

}