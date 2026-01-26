package interfaces;

import model.Ride;

import java.util.Map;

public interface FareStrategy {
    double calculateFare(Map<String, Double> startLocation, Map<String, Double> destination);
}
