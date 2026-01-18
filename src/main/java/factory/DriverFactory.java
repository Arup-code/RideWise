package factory;

import enums.VehicleType;
import model.Driver;
import model.Vehicle;
import util.Helpers;
import util.IdGenerator;

import java.time.LocalDateTime;

public class DriverFactory {
    public static Driver createDriver(String firstName, String lastName, String phoneNumber, VehicleType vehicleType) {
        long driverId = IdGenerator.nextId();
        Vehicle vehicle = new Vehicle("DL-"+driverId, vehicleType, Helpers.generateModelName(vehicleType));
        return new Driver(
                driverId,
                firstName,
                lastName,
                phoneNumber,
                LocalDateTime.now(),
                "RIDEWISE-"+driverId,
                true,
                vehicle,
                Helpers.getRandomRelativeLocation(5)
        );
    }
}
