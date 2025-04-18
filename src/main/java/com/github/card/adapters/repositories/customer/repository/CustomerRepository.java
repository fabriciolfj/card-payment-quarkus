package com.github.card.adapters.repositories.customer.repository;

import com.github.card.adapters.repositories.customer.data.CustomerData;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CustomerRepository implements PanacheRepository<CustomerData> {

    public CustomerData findByCodeOrSave(final CustomerData data) {
        log.info("query customer by coder {}", data.getCode());
        return find("code", data.getCode())
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    save(data);
                    return data;
                });
    }

    public void save(final CustomerData data) {
        persist(data);
    }
}
