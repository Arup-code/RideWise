package service;

import interfaces.FareStrategy;
import interfaces.RideMatchingStrategy;
import model.Ride;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RideService {
    private Map<String, Set<Ride>> rides = new HashMap<>();
    private FareStrategy fareStrategy;
    private RideMatchingStrategy rideMatchingStrategy;

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


}
