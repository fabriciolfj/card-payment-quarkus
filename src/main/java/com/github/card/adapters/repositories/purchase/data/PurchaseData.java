package com.github.card.adapters.repositories.purchase.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "purchase")
@Getter
@Setter
public class PurchaseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchases")
    private List<CardData> cards;

    @Enumerated(EnumType.ORDINAL)
    private TypePurchase type;

    @Column(nullable = true)
    private double latitude;

    @Column(nullable = true)
    private double longitude;

    @Column(nullable = true)
    private double radiusKm;
}
