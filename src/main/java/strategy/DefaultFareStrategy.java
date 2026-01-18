package strategy;

import interfaces.FareStrategy;
import model.Ride;

public class DefaultFareStrategy implements FareStrategy {
    @Override
    public double calculateFare(Ride ride) {
        //TODO: Implement default fare calculation logic
        return 0.0;
    }
}
