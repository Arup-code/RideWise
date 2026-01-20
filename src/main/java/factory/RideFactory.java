package factory;

import enums.RideStatus;
import model.Driver;
import model.Ride;
import model.Rider;
import util.Helpers;
import util.IdGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class RideFactory {
    public Ride createRide(Rider rider, Driver driver, Map<String, Double> startLocation, Map<String, Double> destination, double fare) {
        return new Ride(
                IdGenerator.nextId(),
                List.of(rider),
                driver,
                startLocation,
                destination,
                RideStatus.ASSIGNED,
                Helpers.calculateRideCapacity(driver.getVehicle().getVehicleType()),
                fare,
                false,
                LocalDateTime.now()
        );
    }
}
