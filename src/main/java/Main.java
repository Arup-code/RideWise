import enums.VehicleType;
import exception.NoDriverFoundException;
import interfaces.FareStrategy;
import interfaces.RideMatchingStrategy;
import model.Driver;
import model.Ride;
import model.Rider;
import service.DriverService;
import service.RideService;
import service.RiderService;
import strategy.CashPaymentStrategy;
import strategy.DefaultFareStrategy;
import strategy.NearestDriverStrategy;
import util.Helpers;

public class Main {

    public static void main(String[] args) {

        Helpers.printSection("RideWise Ride Sharing Application");
        RiderService riderService = new RiderService();
        DriverService driverService = new DriverService();
        FareStrategy defaultFareStrategy = new DefaultFareStrategy();
        RideMatchingStrategy rideMatchingStrategy = new NearestDriverStrategy();
        RideService rideService = new RideService(defaultFareStrategy,rideMatchingStrategy);
        System.out.println("...");
        System.out.println("...");

        //Program 1: Add Rider
        Helpers.printSection("Program 1: Add Rider");
        Rider rider1 = riderService.registerRider("John", "Doe", "+919876543210");
        Rider rider2 = riderService.registerRider("Jane", "Smith", "+919123456789");

        //Program 2: Add Driver
        Helpers.printSection("Program 2: Add Driver");
        Driver driver1 = driverService.registerDriver("Alice", "Johnson", "+919112233445", VehicleType.CAR);
        Driver driver2 = driverService.registerDriver("Bob", "Williams", "+919556677889", VehicleType.AUTO);
        Driver driver3 = driverService.registerDriver("Charlie", "Brown", "+919998877665", VehicleType.BIKE);

        //Program 3: Show Available Drivers
        Helpers.printSection("Program 3: Show Available Drivers");
        try {
            driverService.getAvailableDrivers(true);
        } catch (NoDriverFoundException e) {
            throw new RuntimeException(e);
        }

        //Program 4: Request Ride
        Helpers.printSection("Program 4: Request Ride");
        Ride ride1 = rideService.requestRide(rider1, Helpers.getRandomRelativeLocation(10));
        Ride ride2 = rideService.requestRide(rider2, Helpers.getRandomRelativeLocation(15));

        //Program 5: Complete Ride
        Helpers.printSection("Program 5: Complete Ride");
        try {
            rideService.assignDriverToRide(ride1, driverService);
            rideService.assignDriverToRide(ride2, driverService);
        } catch (NoDriverFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("...");
        System.out.println("...");
        rideService.completeRide(ride1.getId(), new CashPaymentStrategy());
        rideService.cancelRide(ride2.getId());

        //Program 6: Show Ride History
        Helpers.printSection("Program 6: Show Ride History");
        rideService.showAllRides();


    }

}
