package com.github.card.adapters.geolocation;

import com.github.card.entities.common.Customer;
import com.github.card.entities.physical.GeoLocation;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.redisson.api.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.*;

@Slf4j
@ApplicationScoped
public class GeoPaymentAdapter {

    private static final String LOCATIONS_KEY = "locations";

    @Inject
    RedissonClient redissonClient;

    @ConfigProperty(name = "payment.max.distance.km", defaultValue = "50.0")
    double maxDistanceKm;

    @ConfigProperty(name = "payment.threshold.amount", defaultValue = "1000.0")
    double thresholdAmount;

    void onStart(@Observes StartupEvent ev) {
        loadInitialGeoData();
    }

    public Boolean evaluatePayment(final GeoLocation geoLocation, final Customer customer, double amount) {
        try {
            final RGeo<String> locations = redissonClient.getGeo(LOCATIONS_KEY);
            final var merchantHash = hashGeoLocation(geoLocation.latitude(), geoLocation.longitude());

            final Map<String, GeoPosition> merchantPositions = locations.pos(merchantHash);
            if (merchantPositions == null || merchantPositions.isEmpty() || merchantPositions.get(merchantHash) == null) {
                log.info("merchant location not found");
                return false;
            }

            final var customerHash = hashGeoLocation(customer.latitude(), customer.longitude());
            final Map<String, GeoPosition> customerPositions = locations.pos(customerHash);
            if (customerPositions == null || customerPositions.isEmpty() || customerPositions.get(customerHash) == null) {
                log.info("customer location not found");
                return false;
            }

            final Double distance = calculateDistance(locations, merchantPositions,
                    merchantHash, customerHash);
            return false;
        } catch (Exception e) {
            throw e;
        }
    }


    private Double calculateDistance(final RGeo<String> locations,
                                     final Map<String, GeoPosition> merchantLocations,
                                     final String merchantHash,
                                     final String customerHash) {
        try {
            final GeoPosition merchPosition = merchantLocations.get(merchantHash);
            locations.add(merchPosition.getLongitude(), merchPosition.getLatitude(), "temp:merchant");

            final Double distance = locations.dist(customerHash, "temp:merchant", GeoUnit.KILOMETERS);

            locations.remove("temp:merchant");

            return distance;
        } catch (Exception e) {
            System.err.println("Erro ao calcular distância: " + e.getMessage());
            return null;
        }
    }

    private int calculateRiskScore(double distance, double amount) {
        // Quanto maior a distância e o valor, maior o risco
        double distanceFactor = Math.min(distance / (maxDistanceKm * 2), 1.0);
        double amountFactor = Math.min(amount / (thresholdAmount * 5), 1.0);

        // Score de 0 a 100, onde 100 é o maior risco
        return (int) Math.round((distanceFactor * 0.6 + amountFactor * 0.4) * 100);
    }

    private static void populateMerchant(final BufferedReader merchantReader, final List<GeoEntry> merchantEntries) throws IOException {
        String line;
        while ((line = merchantReader.readLine()) != null) {
            if (line.startsWith("#") || line.isEmpty()) {
                continue;
            }

            final String[] parts = line.split(",");
            if (parts.length >= 3) {
                double longitude = Double.parseDouble(parts[1].trim());
                double latitude = Double.parseDouble(parts[2].trim());

                merchantEntries.add(new GeoEntry(longitude, latitude, hashGeoLocation(latitude, longitude)));
            }
        }
    }

    private void loadInitialGeoData() {
        try {
            final RGeo<String> merchantLocations = redissonClient.getGeo(LOCATIONS_KEY);
            merchantLocations.delete();

            final InputStream isMerchants = getClass().getResourceAsStream("/data/merchant-locations.csv");
            final BufferedReader merchantReader = new BufferedReader(new InputStreamReader(isMerchants));

            final List<GeoEntry> merchantEntries = new ArrayList<>();
            populateMerchant(merchantReader, merchantEntries);

            if (!merchantEntries.isEmpty()) {
                merchantLocations.add(merchantEntries.toArray(new GeoEntry[0]));
                log.info("carregados {} localizacoes de comerciantes", merchantEntries.size());
            }

            merchantReader.close();
        } catch (Exception e) {
            log.info("erro ao carregar dados de geolocalizacao: {}", e.getMessage());
        }
    }

    private static String hashGeoLocation(final double latitude, final double longitude) {
        final ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putDouble(latitude);
        buffer.putDouble(longitude);
        return Base64.getEncoder().encodeToString(buffer.array());
    }
}
