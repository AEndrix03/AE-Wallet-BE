package it.aredegalli.wallet.entity.portfolio;

import it.aredegalli.wallet.entity.resource.Resource;
import it.aredegalli.wallet.entity.user.User;
import it.aredegalli.wallet.enums.CurrencyEnum;
import it.aredegalli.wallet.enums.portfolio.PortfolioStatusEnum;
import it.aredegalli.wallet.enums.portfolio.PortfolioTypeEnum;
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
@Table(name = "portfolio")
public class Portfolio {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "target", precision = 15, scale = 2)
    private BigDecimal target;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image")
    private Resource image;

    @Convert(converter = CurrencyEnum.CurrencyEnumConverter.class)
    @Column(name = "currency", nullable = false)
    private CurrencyEnum currency;

    @Convert(converter = PortfolioTypeEnum.PortfolioTypeEnumConverter.class)
    @Column(name = "type", nullable = false)
    private PortfolioTypeEnum type;

    @Convert(converter = PortfolioStatusEnum.PortfolioStatusEnumConverter.class)
    @Column(name = "status")
    private PortfolioStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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