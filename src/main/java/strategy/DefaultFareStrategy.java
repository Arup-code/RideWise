package strategy;

import interfaces.FareStrategy;
import model.Ride;
import util.Helpers;

import java.util.Map;

public class DefaultFareStrategy implements FareStrategy {
    private static final double BASE_FARE = 50.0;
    private static final double PER_KM_RATE = 10.0;

    @Override
    public double calculateFare(Ride ride) {
        if (ride == null) return 0.0;
        Map<String, Double> start = ride.getStartLocation();
        Map<String, Double> dest = ride.getDestination();
        if (start == null || dest == null) return 0.0;

        double startLat = start.getOrDefault("latitude", 0.0);
        double startLon = start.getOrDefault("longitude", 0.0);
        double destLat = dest.getOrDefault("latitude", 0.0);
        double destLon = dest.getOrDefault("longitude", 0.0);

        double distanceKm = Helpers.calculateDistance(startLat, startLon, destLat, destLon);
        double fare = BASE_FARE + (distanceKm * PER_KM_RATE);
        // round to 2 decimals
        fare = Math.round(fare * 100.0) / 100.0;
        return fare;
    }
}
