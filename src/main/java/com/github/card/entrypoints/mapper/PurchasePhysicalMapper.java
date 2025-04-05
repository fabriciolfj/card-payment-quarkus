package com.github.card.entrypoints.mapper;

import com.github.card.entities.physical.Purchase;
import com.github.card.entrypoints.dto.PurchasePhysicalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PurchasePhysicalMapper {

    PurchasePhysicalMapper INSTANCE = Mappers.getMapper(PurchasePhysicalMapper.class);


    @Mapping(source = "geo", target = "geoLocation")
    @Mapping(source = "customer.code", target = "customer.code")
    @Mapping(source = "customer.latitude", target = "customer.latitude")
    @Mapping(source = "customer.longitude", target = "customer.longitude")
    @Mapping(source = "expirationDate", target = "card.expirationDate")
    @Mapping(source = "value", target = "card.value")
    @Mapping(source = "crypt", target = "card.crypt")
    Purchase toEntity(PurchasePhysicalDTO dto);
}
