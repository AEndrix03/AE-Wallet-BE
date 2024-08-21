package com.aendrix.aewallet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "wlt_wallet")
@Entity
public class WltWallet {

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
}
