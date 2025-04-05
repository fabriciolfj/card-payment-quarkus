package com.github.card.adapters.purchase;

import com.github.card.adapters.repositories.customer.data.CustomerData;
import com.github.card.entities.common.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerDataMapper {

    CustomerDataMapper INSTANCE = Mappers.getMapper(CustomerDataMapper.class);

    CustomerData toData(Customer customer);
}
