package it.aredegalli.wallet.entity.transaction;

import it.aredegalli.wallet.entity.portfolio.Portfolio;
import it.aredegalli.wallet.enums.CurrencyEnum;
import it.aredegalli.wallet.enums.transaction.TransactionCategoryEnum;
import it.aredegalli.wallet.enums.transaction.TransactionTypeEnum;
import it.aredegalli.wallet.security.encryption.CryptoConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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

    @Column(name = "currency", nullable = false)
    private CurrencyEnum currency;

    @Column(name = "category", nullable = false)
    private TransactionCategoryEnum category;

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