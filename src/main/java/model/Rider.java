package model;

import java.time.LocalDateTime;
import java.util.Map;

public class Rider extends User {
    Map<String,Double> location;

    public Map<String, Double> getLocation() {
        return location;
    }

    public void setLocation(Map<String, Double> location) {
        this.location = location;
    }

    public Rider(long id, String firstName, String lastName, String phoneNumber, LocalDateTime accountCreatedOn, Map<String, Double> location) {
        super(id, firstName, lastName, phoneNumber, accountCreatedOn);
        this.location = location;
    }
}
