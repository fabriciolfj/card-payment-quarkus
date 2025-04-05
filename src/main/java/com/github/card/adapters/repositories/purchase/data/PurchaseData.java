package com.github.card.adapters.repositories.purchase.data;

import com.github.card.adapters.repositories.customer.data.CustomerData;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String code;

    @JoinTable(
            name = "card_purchase",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<CardData> cards;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerData customer;

    @Column()
    private double latitude;

    @Column()
    private double longitude;
}
