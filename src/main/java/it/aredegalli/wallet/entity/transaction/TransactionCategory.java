package it.aredegalli.wallet.entity.transaction;

import it.aredegalli.wallet.enums.transaction.TransactionCategoryEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transaction_category")
public class TransactionCategory {
    @Id
    @Convert(converter = TransactionCategoryEnum.TransactionCategoryEnumConverter.class)
    @Column(name = "code", nullable = false, length = 3)
    private TransactionCategoryEnum code;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

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