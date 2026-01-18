package strategy;

import interfaces.FareStrategy;
import model.Ride;

public class PeakHourFareStrategy implements FareStrategy {
    @Override
    public double calculateFare(Ride ride) {
        return 0;
    }
}
