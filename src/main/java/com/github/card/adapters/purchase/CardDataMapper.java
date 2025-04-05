package com.github.card.adapters.purchase;

import com.github.card.adapters.repositories.purchase.data.CardData;
import com.github.card.entities.common.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardDataMapper {

    CardDataMapper INSTANCE = Mappers.getMapper(CardDataMapper.class);

    @Mapping(source = "value", target = "balance")
    CardData toData(final Card card);

    @Mapping(source = "balance", target = "value")
    Card toEntity(final CardData data);
}
