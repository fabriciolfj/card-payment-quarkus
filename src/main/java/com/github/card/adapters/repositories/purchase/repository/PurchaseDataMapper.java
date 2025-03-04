package com.github.card.adapters.repositories.purchase.repository;

import com.github.card.adapters.repositories.purchase.data.PurchaseData;
import com.github.card.entities.physical.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PurchaseDataMapper {

    PurchaseDataMapper INSTANCE = Mappers.getMapper(PurchaseDataMapper.class);

    PurchaseData toData(Purchase purchase);
}
