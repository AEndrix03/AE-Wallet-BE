package it.aredegalli.wallet.entity.portfolio;

import it.aredegalli.wallet.enums.portfolio.PortfolioStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "portfolio_status")
public class PortfolioStatus {
    @Id
    @Convert(converter = PortfolioStatusEnum.PortfolioStatusEnumConverter.class)
    @Column(name = "code", nullable = false, length = 3)
    private PortfolioStatusEnum code;

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