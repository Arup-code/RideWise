package interfaces;

import model.Driver;
import model.Ride;
import model.Rider;

import java.util.List;

public interface RideMatchingStrategy {
    Driver findDriver(Ride ride, List<Driver> drivers);
}
