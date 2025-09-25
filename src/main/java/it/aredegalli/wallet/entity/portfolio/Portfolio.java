package it.aredegalli.wallet.entity.portfolio;

import it.aredegalli.wallet.entity.resource.Resource;
import it.aredegalli.wallet.entity.user.User;
import it.aredegalli.wallet.enums.CurrencyEnum;
import it.aredegalli.wallet.enums.portfolio.PortfolioStatusEnum;
import it.aredegalli.wallet.enums.portfolio.PortfolioTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "image", insertable = false, updatable = false)
    private Resource image;

    @Column(name = "image")
    private UUID imageId;

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
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

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