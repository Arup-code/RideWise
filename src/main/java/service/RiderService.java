package service;

import exception.NoRiderFoundException;
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
        System.out.println("Hello " + rider.getFirstName() + "  "+ rider.getLastName() +", your Rider ID is: " + rider.getId());
        return rider;
    }

    // Lookup rider by id, return null if not found
    public Rider getRiderById(long id) throws NoRiderFoundException {
        for (Rider r : riders) {
            if (r.getId() == id) return r;
        }
        throw new NoRiderFoundException("Rider with id " + id + " not found");
    }

}
