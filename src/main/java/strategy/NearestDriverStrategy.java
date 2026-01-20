package strategy;

import interfaces.RideMatchingStrategy;
import model.Driver;
import model.Rider;
import util.Helpers;

import java.util.List;
import java.util.Map;

public class NearestDriverStrategy implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        if (rider == null || drivers == null || drivers.isEmpty()) return null;

        Map<String, Double> riderLoc = rider.getLocation();
        if (riderLoc == null) return null;

        double riderLat = riderLoc.getOrDefault("latitude", 0.0);
        double riderLon = riderLoc.getOrDefault("longitude", 0.0);

        Driver best = null;
        double bestDist = Double.MAX_VALUE;

        for (Driver d : drivers) {
            if (d == null || !d.isAvailable()) continue;
            Map<String, Double> drvLoc = d.getCurrentLocation();
            if (drvLoc == null) continue;
            double drvLat = drvLoc.getOrDefault("latitude", 0.0);
            double drvLon = drvLoc.getOrDefault("longitude", 0.0);
            double dist = Helpers.calculateDistance(riderLat, riderLon, drvLat, drvLon);
            if (dist < bestDist) {
                bestDist = dist;
                best = d;
            }
        }
        return best;
    }
}
