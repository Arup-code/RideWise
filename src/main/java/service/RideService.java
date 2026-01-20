package service;

import enums.RideStatus;
import exception.NoDriverFoundException;
import factory.RideFactory;
import interfaces.FareStrategy;
import interfaces.RideMatchingStrategy;
import model.Driver;
import model.Ride;
import model.Rider;

import java.time.LocalDateTime;
import java.util.*;

public class RideService {
    private Map<String, Set<Ride>> rides = new HashMap<>();
    private FareStrategy fareStrategy;
    private RideMatchingStrategy rideMatchingStrategy;

    private RideFactory rideFactory = new RideFactory();
    private DriverService driverService; // injected

    public Map<String, Set<Ride>> getRides() {
        return rides;
    }

    public void setRides(Map<String, Set<Ride>> rides) {
        this.rides = rides;
    }

    public RideMatchingStrategy getRideMatchingStrategy() {
        return rideMatchingStrategy;
    }

    public void setRideMatchingStrategy(RideMatchingStrategy rideMatchingStrategy) {
        this.rideMatchingStrategy = rideMatchingStrategy;
    }

    public FareStrategy getFareStrategy() {
        return fareStrategy;
    }

    public void setFareStrategy(FareStrategy fareStrategy) {
        this.fareStrategy = fareStrategy;
    }

    public DriverService getDriverService() {
        return driverService;
    }

    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    // Request a ride: find a driver using strategy, calculate fare and create a ride
    public Ride requestRide(Rider rider, Map<String, Double> destination) throws NoDriverFoundException {
        if (rider == null) throw new IllegalArgumentException("Rider cannot be null");
        if (destination == null) throw new IllegalArgumentException("Destination cannot be null");
        if (rideMatchingStrategy == null) throw new IllegalStateException("RideMatchingStrategy not set");
        if (fareStrategy == null) throw new IllegalStateException("FareStrategy not set");
        if (driverService == null) throw new IllegalStateException("DriverService not set");

        // get available drivers
        List<Driver> availableDrivers = driverService.getAvailableDrivers();

        // find driver using strategy
        Driver matched = rideMatchingStrategy.findDriver(rider, availableDrivers);
        if (matched == null) throw new NoDriverFoundException("No suitable driver found");

        // create a provisional ride with start/destination to compute fare
        Ride provisional = new Ride(0L, List.of(rider), matched, rider.getLocation(), destination, RideStatus.REQUESTED, 0, 0.0, false, LocalDateTime.now());

        // compute fare
        double fare = fareStrategy.calculateFare(provisional);

        // create final ride via factory (sets status ASSIGNED and capacity)
        Ride ride = rideFactory.createRide(rider, matched, rider.getLocation(), destination, fare);

        // mark driver unavailable
        matched.setAvailable(false);

        // store ride
        String key = String.valueOf(rider.getId());
        Set<Ride> rset = rides.getOrDefault(key, new HashSet<>());
        rset.add(ride);
        rides.put(key, rset);

        return ride;
    }

    // Complete a ride by id: set status, mark driver available and increment completed rides
    public void completeRide(long rideId) {
        Ride found = findRideById(rideId);
        if (found == null) throw new IllegalArgumentException("Ride not found: " + rideId);
        if (found.getRideStatus() == RideStatus.COMPLETED) return;
        found.setRideStatus(RideStatus.COMPLETED);
        found.setRideEndedOn(LocalDateTime.now());
        Driver d = found.getDriver();
        if (d != null) {
            d.setAvailable(true);
            d.setCompletedRides(d.getCompletedRides() + 1);
        }
    }

    public void cancelRide(long rideId) {
        Ride found = findRideById(rideId);
        if (found == null) throw new IllegalArgumentException("Ride not found: " + rideId);
        if (found.getRideStatus() == RideStatus.CANCELLED) return;
        found.setRideStatus(RideStatus.CANCELLED);
        Driver d = found.getDriver();
        if (d != null) d.setAvailable(true);
    }

    // Get rides for a rider id
    public Set<Ride> getRidesForRider(long riderId) {
        return rides.getOrDefault(String.valueOf(riderId), Collections.emptySet());
    }

    // helper to search all rides and return by id
    private Ride findRideById(long rideId) {
        for (Set<Ride> set : rides.values()) {
            for (Ride r : set) {
                if (r.getId() == rideId) return r;
            }
        }
        return null;
    }

}
