package com.github.card.adapters.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocalizationRequestDTO {

    private Double sourceLatitude;
    private Double sourceLongitude;
    private Double targetLatitude;
    private Double targetLongitude;
}
