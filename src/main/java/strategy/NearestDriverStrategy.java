package strategy;

import interfaces.RideMatchingStrategy;
import model.Driver;
import model.Ride;
import util.Helpers;

import java.util.List;
import java.util.Map;

public class NearestDriverStrategy implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Ride ride, List<Driver> drivers) {
        if (ride == null || drivers == null || drivers.isEmpty()) return null;

        Map<String, Double> riderLoc = ride.getStartLocation();
        if (riderLoc == null) return null;

        double riderLat = riderLoc.getOrDefault("latitude", 0.0);
        double riderLon = riderLoc.getOrDefault("longitude", 0.0);

        Driver best = null;
        double bestDist = Double.MAX_VALUE;

        for (Driver driver : drivers) {
            if (driver == null || !driver.isAvailable()) continue;
            if (driver.getCurrentLocation() == null) continue;
            double dist = Helpers.calculateDistance(ride.getStartLocation(), driver.getCurrentLocation());
            if (dist < bestDist) {
                bestDist = dist;
                best = driver;
            }
        }
        return best;
    }
}
