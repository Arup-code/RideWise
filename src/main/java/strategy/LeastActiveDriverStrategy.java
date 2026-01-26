package strategy;

import interfaces.RideMatchingStrategy;
import model.Driver;
import model.Ride;
import util.Helpers;

import java.util.List;
import java.util.Map;

public class LeastActiveDriverStrategy implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Ride ride, List<Driver> drivers) {
        if (ride == null || drivers == null || drivers.isEmpty()) return null;
        Map<String, Double> riderLoc = ride.getStartLocation();
        if (riderLoc == null) return null;

        Driver best = null;
        int bestCompleted = Integer.MAX_VALUE;
        double bestDist = Double.MAX_VALUE;

        for (Driver driver : drivers) {
            if (driver == null || !driver.isAvailable()) continue;
            int completed = driver.getCompletedRides();
            double dist = Helpers.calculateDistance(ride.getStartLocation(), driver.getCurrentLocation());

            if (completed < bestCompleted || (completed == bestCompleted && dist < bestDist)) {
                bestCompleted = completed;
                bestDist = dist;
                best = driver;
            }
        }
        return best;
    }
}
