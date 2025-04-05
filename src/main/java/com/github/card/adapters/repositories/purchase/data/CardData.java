package com.github.card.adapters.repositories.purchase.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String crypt;

    @Column(name = "expiration_date", columnDefinition = "TIMESTAMP")
    private LocalDate expirationDate;

    private BigDecimal balance;

    @ManyToMany(mappedBy = "cards")
    private List<PurchaseData> purchases;
}
