package com.github.card.entrypoints.mapper;

import com.github.card.entities.physical.Purchase;
import com.github.card.entrypoints.dto.PurchaseResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface PurchaseResponseMapper {

    PurchaseResponseMapper INSTANCE = Mappers.getMapper(PurchaseResponseMapper.class);
    
    @Mapping(source = "card", target = "card")
    @Mapping(source = "geoLocation", target = "geoLocation")
    @Mapping(target = "type", constant = "PHYSICAL")
    PurchaseResponseDTO toDto(Purchase purchase);
    
    @Mapping(source = "crypt", target = "crypt")
    @Mapping(source = "expirationDate", target = "expirationDate", qualifiedByName = "formatDateTime")
    @Mapping(source = "balance", target = "balance")
    PurchaseResponseDTO.CardDTO toCardDto(com.github.card.entities.common.Card card);
    
    @Named("formatDateTime")
    default String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}