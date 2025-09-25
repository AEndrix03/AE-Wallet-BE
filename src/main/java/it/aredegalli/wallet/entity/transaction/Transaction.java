package it.aredegalli.wallet.entity.transaction;

import it.aredegalli.wallet.entity.portfolio.Portfolio;
import it.aredegalli.wallet.enums.CurrencyEnum;
import it.aredegalli.wallet.enums.transaction.TransactionCategoryEnum;
import it.aredegalli.wallet.enums.transaction.TransactionTypeEnum;
import it.aredegalli.wallet.security.encryption.CryptoConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Convert(converter = CryptoConverter.class)
    @Column(name = "description", length = 128)
    private String description;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Convert(converter = CryptoConverter.class)
    @Column(name = "note", length = Integer.MAX_VALUE)
    private String note;

    @Convert(converter = CurrencyEnum.CurrencyEnumConverter.class)
    @Column(name = "currency", nullable = false)
    private CurrencyEnum currency;

    @Convert(converter = TransactionCategoryEnum.TransactionCategoryEnumConverter.class)
    @Column(name = "category", nullable = false)
    private TransactionCategoryEnum category;

    @Convert(converter = TransactionTypeEnum.TransactionTypeEnumConverter.class)
    @Column(name = "type", nullable = false)
    private TransactionTypeEnum type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "portfolio", nullable = false)
    private Portfolio portfolio;

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