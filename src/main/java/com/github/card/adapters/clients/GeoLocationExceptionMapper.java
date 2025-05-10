package com.github.card.adapters.clients;

import com.github.card.exceptions.exception.GeoLocationException;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

@Provider
public class GeoLocationExceptionMapper implements ResponseExceptionMapper<GeoLocationException> {

    @Override
    public GeoLocationException toThrowable(Response response) {
        if (response.getStatus() == 400) {
            return new GeoLocationException("Requisição inválida para o serviço de geolocalização");
        } else if (response.getStatus() == 404) {
            return new GeoLocationException("Recurso de geolocalização não encontrado");
        } else if (response.getStatus() >= 500) {
            return new GeoLocationException("Erro no servidor de geolocalização");
        }
        return new GeoLocationException("Erro desconhecido: " + response.getStatus());
    }

    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        return status >= 400;
    }
}