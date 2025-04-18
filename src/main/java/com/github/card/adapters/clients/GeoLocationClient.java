package com.github.card.adapters.clients;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v1/api/geo")
@RegisterRestClient(configKey = "geo-client")
public interface GeoLocationClient {

    @POST
    GeoLocalizationResponseDTO request(@RequestBody final GeoLocalizationRequestDTO dto);

}
