package com.github.card.adapters.repositories.purchase.data;

import com.github.card.adapters.repositories.customer.data.CustomerData;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    
    @Column(nullable = false, unique = true)
    private String code;

    @JoinTable(
            name = "card_purchase",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<CardData> cards;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_id")
    private List<PurchaseStatusData> status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerData customer;

    @Column(name = "registry", nullable = false)
    private LocalDateTime registry;

    @Column()
    private double latitude;

    @Column()
    private double longitude;

    public List<String> getStatusDescribe() {
        return status.stream().map(PurchaseStatusData::getStatus).toList();
    }

    public PurchaseData addStatus(final List<PurchaseStatusData> statusDatas) {
        this.getStatus().addAll(statusDatas);
        return this;
    }
}
