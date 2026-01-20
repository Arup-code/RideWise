package model;

import enums.RideStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ride {
    private long id;
    private List<Rider> riders;
    private Driver driver;
    private Map<String, Double> startLocation;
    private Map<String, Double> destination;
    private RideStatus rideStatus;
    private int rideCapacity;
    private double fare;
    private boolean isPaid;
    private LocalDateTime rideCreatedOn;
    private LocalDateTime rideEndedOn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Rider> getRiders() {
        return riders;
    }

    public void setRiders(List<Rider> riders) {
        this.riders = riders;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Map<String, Double> getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Map<String, Double> startLocation) {
        this.startLocation = startLocation;
    }

    public Map<String, Double> getDestination() {
        return destination;
    }

    public void setDestination(Map<String, Double> destination) {
        this.destination = destination;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public int getRideCapacity() {
        return rideCapacity;
    }

    public void setRideCapacity(int rideCapacity) {
        this.rideCapacity = rideCapacity;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public LocalDateTime getRideCreatedOn() {
        return rideCreatedOn;
    }

    public void setRideCreatedOn(LocalDateTime rideCreatedOn) {
        this.rideCreatedOn = rideCreatedOn;
    }

    public LocalDateTime getRideEndedOn() {
        return rideEndedOn;
    }

    public void setRideEndedOn(LocalDateTime rideEndedOn) {
        this.rideEndedOn = rideEndedOn;
    }

    public Ride(long id, List<Rider> riders, Driver driver, Map<String, Double> startLocation, Map<String, Double> destination, RideStatus rideStatus, int rideCapacity, double fare, boolean isPaid, LocalDateTime rideCreatedOn) {
        this.id = id;
        this.riders = riders;
        this.driver = driver;
        this.startLocation = startLocation;
        this.destination = destination;
        this.rideStatus = rideStatus;
        this.rideCapacity = rideCapacity;
        this.fare = fare;
        this.isPaid = isPaid;
        this.rideCreatedOn = rideCreatedOn;
    }
}
