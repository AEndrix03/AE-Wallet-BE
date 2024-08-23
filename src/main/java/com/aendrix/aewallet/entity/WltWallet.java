package com.aendrix.aewallet.entity;

import com.aendrix.aewallet.dto.wallets.WalletDto;
import com.aendrix.aewallet.utils.DtoMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "wlt_wallet")
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class WltWallet implements DtoMapper<WalletDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private WltUser wltUser;

    @Override
    public WalletDto toDto() {
        return WalletDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }
}
