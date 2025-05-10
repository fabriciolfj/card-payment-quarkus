package com.github.card.adapters.clients;

import com.github.card.exceptions.exception.GeoLocationException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v1/api/geo")
@RegisterRestClient(configKey = "geo-client")
@RegisterProvider(GeoLocationException.class)
public interface GeoLocationClient {

    @POST
    GeoLocalizationResponseDTO request(@RequestBody final GeoLocalizationRequestDTO dto);
}
