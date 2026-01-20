package strategy;

import interfaces.RideMatchingStrategy;
import model.Driver;
import model.Rider;
import util.Helpers;

import java.util.List;
import java.util.Map;

public class LeastActiveDriverStrategy implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        if (rider == null || drivers == null || drivers.isEmpty()) return null;
        Map<String, Double> riderLoc = rider.getLocation();
        if (riderLoc == null) return null;

        double riderLat = riderLoc.getOrDefault("latitude", 0.0);
        double riderLon = riderLoc.getOrDefault("longitude", 0.0);

        Driver best = null;
        int bestCompleted = Integer.MAX_VALUE;
        double bestDist = Double.MAX_VALUE;

        for (Driver d : drivers) {
            if (d == null || !d.isAvailable()) continue;
            int completed = d.getCompletedRides();
            Map<String, Double> drvLoc = d.getCurrentLocation();
            double dist = Double.MAX_VALUE;
            if (drvLoc != null) {
                double drvLat = drvLoc.getOrDefault("latitude", 0.0);
                double drvLon = drvLoc.getOrDefault("longitude", 0.0);
                dist = Helpers.calculateDistance(riderLat, riderLon, drvLat, drvLon);
            }

            if (completed < bestCompleted || (completed == bestCompleted && dist < bestDist)) {
                bestCompleted = completed;
                bestDist = dist;
                best = d;
            }
        }
        return best;
    }
}
