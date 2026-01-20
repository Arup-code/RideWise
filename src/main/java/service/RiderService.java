package service;

import factory.RiderFactory;
import model.Rider;

import java.util.ArrayList;
import java.util.List;

public class RiderService {
    List<Rider> riders = new ArrayList<Rider>();

    public List<Rider> getRiders() {
        return riders;
    }

    public void setRiders(List<Rider> riders) {
        this.riders = riders;
    }

    // Register a new rider using RiderFactory and add to in-memory list
    public Rider registerRider(String firstName, String lastName, String phoneNumber) {
        Rider rider = RiderFactory.createRider(firstName, lastName, phoneNumber);
        this.riders.add(rider);
        return rider;
    }

    // Lookup rider by id, return null if not found
    public Rider getRiderById(long id) {
        for (Rider r : riders) {
            if (r.getId() == id) return r;
        }
        return null;
    }

}
