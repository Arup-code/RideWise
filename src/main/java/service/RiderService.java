package service;

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


}
