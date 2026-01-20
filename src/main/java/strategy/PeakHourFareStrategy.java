package strategy;

import interfaces.FareStrategy;
import model.Ride;

import java.time.LocalDateTime;

public class PeakHourFareStrategy implements FareStrategy {
    private static final double PEAK_MULTIPLIER = 1.5;
    private DefaultFareStrategy base = new DefaultFareStrategy();

    private boolean isPeakHour(LocalDateTime time) {
        if (time == null) return false;
        int hour = time.getHour();
        // morning peak 7-10, evening peak 17-20
        return (hour >= 7 && hour <= 10) || (hour >= 17 && hour <= 20);
    }

    @Override
    public double calculateFare(Ride ride) {
        double baseFare = base.calculateFare(ride);
        LocalDateTime created = ride.getRideCreatedOn();
        if (isPeakHour(created)) {
            double fare = baseFare * PEAK_MULTIPLIER;
            fare = Math.round(fare * 100.0) / 100.0;
            return fare;
        }
        return baseFare;
    }
}
