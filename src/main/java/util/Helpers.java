package util;

import enums.VehicleType;

import java.util.Map;

public class Helpers {

    public static double calculateDistance(Map<String, Double> startLocation, Map<String, Double> destination) {
        if (startLocation == null || destination == null) return 0.0;

        double lat1 = startLocation.getOrDefault("latitude", 0.0);
        double lon1 = startLocation.getOrDefault("longitude", 0.0);
        double lat2 = destination.getOrDefault("latitude", 0.0);
        double lon2 = destination.getOrDefault("longitude", 0.0);

        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double lon1Rad = Math.toRadians(lon1);
        double lon2Rad = Math.toRadians(lon2);

        double x = (lon2Rad - lon1Rad) * Math.cos((lat1Rad + lat2Rad) / 2);
        double y = (lat2Rad - lat1Rad);

        return Math.sqrt(x * x + y * y) * Constants.EARTH_RADIUS;
    }

    public static int calculateRideCapacity(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> 4;
            case AUTO -> 3;
            case BIKE -> 1;
            default -> 0;
        };
    }

    public static String generateModelName(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> Constants.CAR_MODELS.get((int) (Math.random() * Constants.CAR_MODELS.size()));
            case AUTO -> Constants.AUTO_MODELS.get((int) (Math.random() * Constants.AUTO_MODELS.size()));
            case BIKE -> Constants.BIKE_MODELS.get((int) (Math.random() * Constants.BIKE_MODELS.size()));
        };
    }

    public static Map<String,Double> getRandomRelativeLocation(double maxOffset) {
        double latOffset = (Math.random() * 2 - 1) * maxOffset;
        double lonOffset = (Math.random() * 2 - 1) * maxOffset;
        return Map.of(
                "latitude", Constants.DUMMYLAT + latOffset,
                "longitude", Constants.DUMMYLONG + lonOffset
        );
    }

    public static void printSection(String title) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("======== "+title+" ========");
        System.out.println("------------------------------------------------------------------");
    }
}
