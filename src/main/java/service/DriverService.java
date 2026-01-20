package service;

import enums.VehicleType;
import exception.NoDriverFoundException;
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

}
