package model;

import java.time.LocalDateTime;
import java.util.Map;

public class Driver extends User{
    private String licenseNumber;
    private boolean isAvailable;
    private Vehicle vehicle;
    private Map<String,Double> currentLocation;

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Map<String, Double> getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Map<String, Double> currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Driver(long id, String firstName, String lastName, String phoneNumber, LocalDateTime accountCreatedOn, String licenseNumber, boolean isAvailable, Vehicle vehicle, Map<String, Double> currentLocation) {
        super(id, firstName, lastName, phoneNumber, accountCreatedOn);
        this.licenseNumber = licenseNumber;
        this.isAvailable = isAvailable;
        this.vehicle = vehicle;
        this.currentLocation = currentLocation;
    }
}
