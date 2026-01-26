package model;

import java.time.LocalDateTime;

public class FareReceipt {
    private long rideId;
    private double amount;
    private LocalDateTime generatedOn;

    public long getRideId() {
        return rideId;
    }

    public void setRideId(long rideId) {
        this.rideId = rideId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(LocalDateTime generatedOn) {
        this.generatedOn = generatedOn;
    }

    public FareReceipt(long rideId, double amount, LocalDateTime generatedOn) {
        this.rideId = rideId;
        this.amount = amount;
        this.generatedOn = generatedOn;
    }
}
