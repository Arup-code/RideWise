package strategy;

import interfaces.FareStrategy;
import model.Ride;
import util.Helpers;

import java.util.Map;

import static util.Constants.BASE_FARE;
import static util.Constants.PER_KM_RATE;

public class DefaultFareStrategy implements FareStrategy {

    @Override
    public double calculateFare(Map<String, Double> startLocation, Map<String, Double> destination) {
        double distanceKm = Helpers.calculateDistance(startLocation, destination);
        double fare = BASE_FARE + (distanceKm * PER_KM_RATE);
        return Math.round(fare * 100.0) / 100.0;
    }
}
