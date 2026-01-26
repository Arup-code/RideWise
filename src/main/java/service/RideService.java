package service;

import enums.RideStatus;
import exception.NoDriverFoundException;
import factory.RideFactory;
import interfaces.FareStrategy;
import interfaces.PaymentMethodStrategy;
import interfaces.RideMatchingStrategy;
import model.Driver;
import model.FareReceipt;
import model.Ride;
import model.Rider;

import java.time.LocalDateTime;
import java.util.*;

public class RideService {
    private Map<RideStatus, Set<Ride>> rides = new HashMap<>();
    private List<FareReceipt> fareReceipts = new ArrayList<>();
    private FareStrategy fareStrategy;
    private RideMatchingStrategy rideMatchingStrategy;

    public Map<RideStatus, Set<Ride>> getRides() {
        return rides;
    }

    public void setRides(Map<RideStatus, Set<Ride>> rides) {
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

    public List<FareReceipt> getFareReceipts() {
        return fareReceipts;
    }

    public void setFareReceipts(List<FareReceipt> fareReceipts) {
        this.fareReceipts = fareReceipts;
    }

    public Ride requestRide(Rider rider, Map<String, Double> destination) throws IllegalArgumentException, IllegalStateException {
        if (rider == null) throw new IllegalArgumentException("Rider cannot be null");
        if (destination == null) throw new IllegalArgumentException("Destination cannot be null");
        if (rideMatchingStrategy == null) throw new IllegalStateException("RideMatchingStrategy not set");
        if (fareStrategy == null) throw new IllegalStateException("FareStrategy not set");

        // compute fare
        double fare = fareStrategy.calculateFare(rider.getLocation(),destination);

        // create final ride via factory (sets status ASSIGNED and capacity)
        Ride ride = RideFactory.createRide(rider, rider.getLocation(), destination, fare);

        // store ride
        Set<Ride> rideSet = rides.getOrDefault(RideStatus.REQUESTED, new HashSet<>());
        rideSet.add(ride);
        rides.put(RideStatus.REQUESTED, rideSet);
        System.out.println("Ride requested: #" + ride.getId() + ", Fare Rs" + ride.getFare());
        return ride;
    }

    public void assignDriverToRide(Ride ride, DriverService driverService) throws NoDriverFoundException {
        Driver driver = driverService.searchForDriver(ride, rideMatchingStrategy);
        if (driver == null) {
            cancelRide(ride.getId());
            throw new NoDriverFoundException("No driver found for ride id: #" + ride.getId());
        }
        // assign driver to ride
        ride.setDriver(driver);
        ride.setRideStatus(RideStatus.ASSIGNED);
        driver.setAvailable(false);

        // move ride from REQUESTED to ASSIGNED
        Set<Ride> requestedRides = rides.get(RideStatus.REQUESTED);
        requestedRides.remove(ride);
        rides.put(RideStatus.REQUESTED, requestedRides);

        Set<Ride> assignedRides = rides.getOrDefault(RideStatus.ASSIGNED, new HashSet<>());
        assignedRides.add(ride);
        rides.put(RideStatus.ASSIGNED, assignedRides);
        System.out.println("Driver " + driver.getFirstName() + " " + driver.getLastName() + " assigned to ride id: #" + ride.getId());
    }

    public void completeRide(long rideId, PaymentMethodStrategy paymentMethodStrategy) {
        Ride found = findRideById(rideId);
        if (found == null) throw new IllegalArgumentException("Ride not found: " + rideId);
        if (found.getRideStatus() == RideStatus.COMPLETED) return;

        //Collect payment
        boolean paymentStatus = paymentMethodStrategy.pay(found.getFare());
        if (!paymentStatus) return;
        FareReceipt fareReceipt = new FareReceipt(found.getId(), found.getFare(), LocalDateTime.now());
        fareReceipts.add(fareReceipt);
        found.setPaid(true);

        // move ride from ASSIGNED to COMPLETED
        Set<Ride> assignedRides = rides.get(RideStatus.ASSIGNED);
        assignedRides.remove(found);
        rides.put(RideStatus.ASSIGNED, assignedRides);
        found.setRideStatus(RideStatus.COMPLETED);
        found.setRideEndedOn(LocalDateTime.now());
        Set<Ride> completedRides = rides.getOrDefault(RideStatus.COMPLETED, new HashSet<>());
        completedRides.add(found);
        rides.put(RideStatus.COMPLETED, completedRides);

        Driver driver = found.getDriver();
        if (driver != null) {
            driver.setAvailable(true);
            driver.setCurrentLocation(found.getDestination());
            driver.setCompletedRides(driver.getCompletedRides()+1);
        }

        Rider rider = found.getRider();
        if (rider != null) {
            rider.setLocation(found.getDestination());
        }
        System.out.println("Ride with id #" + rideId + " completed successfully. Fare paid: Rs" + found.getFare());
    }

    public void cancelRide(long rideId) {
        Ride found = findRideById(rideId);
        if (found == null) throw new IllegalArgumentException("Ride not found: " + rideId);
        if (found.getRideStatus() == RideStatus.CANCELLED) return;
        if(found.getRideStatus() == RideStatus.REQUESTED) {
            Set<Ride> requestedRides = rides.get(RideStatus.REQUESTED);
            requestedRides.remove(found);
            rides.put(RideStatus.REQUESTED, requestedRides);
        }
        if(found.getRideStatus() == RideStatus.ASSIGNED) {
            Set<Ride> assignedRides = rides.get(RideStatus.ASSIGNED);
            assignedRides.remove(found);
            rides.put(RideStatus.ASSIGNED, assignedRides);
        }
        found.setRideStatus(RideStatus.CANCELLED);
        Set<Ride> cancelledRides = rides.getOrDefault(RideStatus.CANCELLED, new HashSet<>());
        cancelledRides.add(found);
        rides.put(RideStatus.CANCELLED, cancelledRides);
        Driver driver = found.getDriver();
        if (driver != null) driver.setAvailable(true);
        System.out.println("Ride with id #" + rideId + " cancelled successfully.");
    }


    public Ride findRideById(long rideId) {
        for ( Ride ride : rides.getOrDefault(RideStatus.ASSIGNED, new HashSet<Ride>())) {
            if (ride.getId() == rideId) return ride;
        }
        for ( Ride ride : rides.getOrDefault(RideStatus.REQUESTED, new HashSet<Ride>())) {
            if (ride.getId() == rideId) return ride;
        }
        for ( Ride ride : rides.getOrDefault(RideStatus.COMPLETED, new HashSet<Ride>())) {
            if (ride.getId() == rideId) return ride;
        }
        for ( Ride ride : rides.getOrDefault(RideStatus.CANCELLED, new HashSet<Ride>())) {
            if (ride.getId() == rideId) return ride;
        }
        return null;
    }

    public void showAllRides() {
        System.out.println("Assigned Rides");
        for ( Ride ride : rides.getOrDefault(RideStatus.ASSIGNED, new HashSet<Ride>())) {
            System.out.println(ride);
        }
        System.out.println("Requested Rides");
        for ( Ride ride : rides.getOrDefault(RideStatus.REQUESTED, new HashSet<Ride>())) {
            System.out.println(ride);
        }
        System.out.println("Completed Rides");
        for ( Ride ride : rides.getOrDefault(RideStatus.COMPLETED, new HashSet<Ride>())) {
            System.out.println(ride);
        }
        System.out.println("Cancelled Rides");
        for ( Ride ride : rides.getOrDefault(RideStatus.CANCELLED, new HashSet<Ride>())) {
            System.out.println(ride);
        }
    }

    public RideService(FareStrategy fareStrategy, RideMatchingStrategy rideMatchingStrategy) {
        this.rides = new HashMap<>();
        rides.put(RideStatus.REQUESTED, new HashSet<>());
        rides.put(RideStatus.ASSIGNED, new HashSet<>());
        rides.put(RideStatus.CANCELLED, new HashSet<>());
        rides.put(RideStatus.COMPLETED, new HashSet<>());
        this.fareStrategy = fareStrategy;
        this.rideMatchingStrategy = rideMatchingStrategy;
    }
}
