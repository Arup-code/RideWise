package strategy;

import interfaces.RideMatchingStrategy;
import model.Driver;
import model.Rider;

import java.util.List;

public class NearestDriverStrategy implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        return null;
    }
}
