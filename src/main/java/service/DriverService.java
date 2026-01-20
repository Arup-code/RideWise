package service;

import enums.VehicleType;
import exception.NoDriverFoundException;
import factory.DriverFactory;
import model.Driver;
import model.Rider;

import java.util.ArrayList;
import java.util.List;

public class DriverService {
    private List<Driver> drivers = new ArrayList<Driver>();

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Driver> getAvailableDrivers() throws NoDriverFoundException {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                availableDrivers.add(driver);
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
        return driver;
    }

    // Find driver by id
    public Driver getDriverById(long id) {
        for (Driver d : drivers) {
            if (d.getId() == id) return d;
        }
        return null;
    }

    // Update driver availability
    public void setDriverAvailability(long driverId, boolean available) {
        Driver d = getDriverById(driverId);
        if (d != null) d.setAvailable(available);
    }

    // Update driver current location
    public void updateDriverLocation(long driverId, java.util.Map<String, Double> location) {
        Driver d = getDriverById(driverId);
        if (d != null) d.setCurrentLocation(location);
    }

}
