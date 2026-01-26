package service;

import enums.VehicleType;
import exception.NoDriverFoundException;
import factory.DriverFactory;
import interfaces.RideMatchingStrategy;
import model.Driver;
import model.Ride;
import model.Rider;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class DriverService {
    private List<Driver> drivers = new ArrayList<Driver>();

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Driver> getAvailableDrivers(boolean printResults) throws NoDriverFoundException {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                availableDrivers.add(driver);
                if(printResults) {
                    System.out.println("Available Driver: " + driver.getFirstName() + " " + driver.getLastName() + ", Vehicle: " + driver.getVehicle().getLicensePlate());
                }
            }
        }
        if (availableDrivers.isEmpty()) {
            throw new NoDriverFoundException("No drivers are available");
        }
        return availableDrivers;
    }

    public List<Driver> getDriversByVehicleType(VehicleType vehicleType) throws NoDriverFoundException {
        List<Driver> matchedDrivers = new ArrayList<>();
        for (Driver driver : drivers) {
            if (driver.getVehicle().getVehicleType() == vehicleType) {
                matchedDrivers.add(driver);
            }
        }
        if (matchedDrivers.isEmpty()) {
            throw new NoDriverFoundException("No drivers found for vehicle type: " + vehicleType);
        }
        return matchedDrivers;
    }

    // Register a new driver using DriverFactory and add to list
    public Driver registerDriver(String firstName, String lastName, String phoneNumber, VehicleType vehicleType) {
        Driver driver = DriverFactory.createDriver(firstName, lastName, phoneNumber, vehicleType);
        this.drivers.add(driver);
        System.out.println("Hello " + driver.getFirstName() + "  "+ driver.getLastName() +", your Driver ID is: #" + driver.getId());
        return driver;
    }

    // Find driver by id
    public Driver getDriverById(long id) throws NoDriverFoundException {
        for (Driver d : drivers) {
            if (d.getId() == id) return d;
        }
        throw new NoDriverFoundException("Driver with #" + id + " not found");
    }

    // Update driver availability
    public void setDriverAvailability(long driverId, boolean available) throws NoDriverFoundException {
        Driver driver = getDriverById(driverId);
        if (driver != null) driver.setAvailable(available);
    }

    // Update driver current location
    public void updateDriverLocation(long driverId, java.util.Map<String, Double> location) throws NoDriverFoundException {
        Driver driver = getDriverById(driverId);
        if (driver != null) driver.setCurrentLocation(location);
    }

    public Driver searchForDriver(Ride ride, RideMatchingStrategy rideMatchingStrategy) throws NoDriverFoundException {
        System.out.println("Searching for driver for ride id: " + ride.getId());
        Driver driver = rideMatchingStrategy.findDriver(ride, this.getAvailableDrivers(false));
        System.out.println("Driver found: " + driver);
        return driver;
    }

}
