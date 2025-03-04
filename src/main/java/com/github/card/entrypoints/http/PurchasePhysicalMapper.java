package com.github.card.entrypoints.http;

import com.github.card.entities.physical.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PurchasePhysicalMapper {

    PurchasePhysicalMapper INSTANCE = Mappers.getMapper(PurchasePhysicalMapper.class);


    @Mapping(source = "geo", target = "geoLocation")
    @Mapping(source = "customer", target = "customer.code")
    @Mapping(source = "expirationDate", target = "card.expirationDate")
    @Mapping(source = "value", target = "card.value")
    @Mapping(source = "crypt", target = "card.crypt")
    Purchase toEntity(PurchasePhysicalDTO dto);
}
