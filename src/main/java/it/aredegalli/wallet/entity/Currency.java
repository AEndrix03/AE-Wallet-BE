package it.aredegalli.wallet.entity;

import it.aredegalli.wallet.enums.CurrencyEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @Convert(converter = CurrencyEnum.CurrencyEnumConverter.class)
    @Column(name = "code", nullable = false, length = 3)
    private CurrencyEnum code;

    @Column(name = "symbol", nullable = false, length = 1)
    private String symbol;

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