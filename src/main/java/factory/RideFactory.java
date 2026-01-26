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
    public static Ride createRide(Rider rider, Map<String, Double> startLocation, Map<String, Double> destination, double fare) {
        return new Ride(
                IdGenerator.nextId(),
                rider,
                startLocation,
                destination,
                RideStatus.ASSIGNED,
                fare,
                false,
                LocalDateTime.now()
        );
    }
}
