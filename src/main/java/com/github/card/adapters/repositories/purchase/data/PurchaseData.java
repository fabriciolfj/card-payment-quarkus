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

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "purchases")
    private List<CardData> cards;

    @Enumerated(EnumType.ORDINAL)
    private TypePurchase type;

    @Column()
    private double latitude;

    @Column()
    private double longitude;

    @Column()
    private double radiusKm;
}
