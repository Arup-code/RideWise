package factory;

import model.Rider;
import util.Helpers;
import util.IdGenerator;

import java.time.LocalDateTime;
import java.util.Map;

public class RiderFactory {
    public static Rider createRider(String firstName, String lastName, String phoneNumber) {
        long id = IdGenerator.nextId();
        Map<String, Double> location = Helpers.getRandomRelativeLocation(3);
        return new Rider(id, firstName, lastName, phoneNumber, LocalDateTime.now(), location);
    }
}
