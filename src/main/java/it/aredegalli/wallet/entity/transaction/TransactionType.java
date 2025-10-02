package it.aredegalli.wallet.entity.transaction;

import it.aredegalli.wallet.enums.transaction.TransactionTypeEnum;
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
@Table(name = "transaction_type")
public class TransactionType {
    @Id
    @Column(name = "code", nullable = false, length = 3, columnDefinition = "VARCHAR(3)")
    private String id;

    @Column(name = "code", nullable = false, insertable = false, updatable = false, length = 3, columnDefinition = "VARCHAR(3)")
    private TransactionTypeEnum code;

    @Column(name = "name", nullable = false, length = 16)
    private String name;

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