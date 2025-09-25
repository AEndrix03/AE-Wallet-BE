package it.aredegalli.wallet.entity.resource;

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
@Table(name = "resource")
public class Resource {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @ColumnDefault("now()")
    @Column(name = "upload_date", nullable = false)
    private Instant uploadDate;

    @Column(name = "key", nullable = false, length = Integer.MAX_VALUE)
    private String key;

    @Column(name = "bucket", nullable = false, length = 32)
    private String bucket;

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