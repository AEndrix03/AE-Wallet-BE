package it.aredegalli.wallet.dto.portfolio.update;

import it.aredegalli.wallet.dto.portfolio.PortfolioDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioSaveDto extends PortfolioDto {

    private UUID userId;

}
