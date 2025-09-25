package it.aredegalli.wallet.mapper.portfolio;

import it.aredegalli.wallet.dto.portfolio.PortfolioDto;
import it.aredegalli.wallet.entity.portfolio.Portfolio;
import it.aredegalli.wallet.mapper.base.IDtoMapper;
import it.aredegalli.wallet.mapper.base.IEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMapper implements IDtoMapper<PortfolioDto, Portfolio>, IEntityMapper<PortfolioDto, Portfolio> {

    @Override
    public PortfolioDto toDto(Portfolio entity) {
        return PortfolioDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .type(entity.getType())
                .balance(null)
                .target(entity.getTarget())
                .image(entity.getImage() != null ? entity.getImage().getId() : null)
                .currency(entity.getCurrency())
                .lastUpdated(entity.getUpdateDate() != null ? entity.getUpdateDate().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime() : null)
                .build();
    }

    @Override
    public Portfolio toEntity(PortfolioDto dto) {
        return Portfolio.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .type(dto.getType())
                .target(dto.getTarget())
                .imageId(dto.getImage())
                .currency(dto.getCurrency())
                .build();
    }
}
