package model;

import enums.VehicleType;

public class Vehicle {
    private String licensePlate;
    private VehicleType vehicleType;
    private String model;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Vehicle(String licensePlate, VehicleType vehicleType, String model) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.model = model;
    }
}
