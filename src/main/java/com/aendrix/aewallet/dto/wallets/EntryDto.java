package com.aendrix.aewallet.dto.wallets;

import com.aendrix.aewallet.entity.WltEntry;
import com.aendrix.aewallet.utils.EntityMapper;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class EntryDto implements EntityMapper<WltEntry> {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private Float value;
    private Long walletId;

    @Override
    public WltEntry toEntity() {
        return WltEntry.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .date(this.date)
                .value(this.value)
                .build();
    }
}
