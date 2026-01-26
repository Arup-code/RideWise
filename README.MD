# RideWise - Ride Sharing Application

## 📋 Overview
RideWise is a comprehensive ride-sharing application built with Java that demonstrates key software design patterns and principles. The system facilitates ride booking between riders and drivers with dynamic fare calculation, intelligent driver matching, and flexible payment options.

## 🎯 Features

### Core Functionality
1. **Rider Management**
   - Register new riders with unique IDs
   - Track rider locations
   - Manage rider ride history

2. **Driver Management**
   - Register drivers with vehicle information
   - Track driver availability and location
   - Monitor completed rides count
   - Support multiple vehicle types (Bike, Auto, Car)

3. **Ride Management**
   - Request rides with start and destination locations
   - Intelligent driver assignment using matching strategies
   - Real-time ride status tracking (Requested, Assigned, Completed, Cancelled)
   - Complete or cancel rides

4. **Dynamic Fare Calculation**
   - Base fare + per kilometer rate calculation
   - Peak hour pricing support with multiplier
   - Distance-based fare computation using Haversine formula

5. **Flexible Payment Methods**
   - Cash payment support
   - UPI payment integration
   - Strategy pattern for extensible payment options

6. **Driver Matching Strategies**
   - **Nearest Driver**: Assigns the closest available driver to the rider
   - **Least Active Driver**: Prioritizes drivers with fewer completed rides

## 🏗️ Architecture & Design Patterns

### Design Patterns Implemented

1. **Factory Pattern**
   - `DriverFactory`: Creates driver instances with vehicles
   - `RiderFactory`: Creates rider instances with initial locations
   - `RideFactory`: Creates ride instances with proper initialization

2. **Strategy Pattern**
   - **Fare Strategies**: `DefaultFareStrategy`, `PeakHourFareStrategy`
   - **Payment Strategies**: `CashPaymentStrategy`, `UPIPaymentStrategy`
   - **Ride Matching Strategies**: `NearestDriverStrategy`, `LeastActiveDriverStrategy`

3. **Service Layer Architecture**
   - `RiderService`: Handles all rider-related operations
   - `DriverService`: Manages driver operations and availability
   - `RideService`: Orchestrates ride lifecycle management

### Project Structure
```
src/main/java/
├── Main.java                          # Application entry point
├── enums/
│   ├── RideStatus.java               # Ride states (REQUESTED, ASSIGNED, COMPLETED, CANCELLED)
│   └── VehicleType.java              # Vehicle types (BIKE, AUTO, CAR)
├── exception/
│   ├── NoDriverFoundException.java   # Custom exception for driver unavailability
│   └── NoRiderFoundException.java    # Custom exception for rider not found
├── factory/
│   ├── DriverFactory.java            # Factory for creating drivers
│   ├── RideFactory.java              # Factory for creating rides
│   └── RiderFactory.java             # Factory for creating riders
├── interfaces/
│   ├── FareStrategy.java             # Interface for fare calculation strategies
│   ├── PaymentMethodStrategy.java    # Interface for payment methods
│   └── RideMatchingStrategy.java     # Interface for driver matching strategies
├── model/
│   ├── Driver.java                   # Driver entity with vehicle and location
│   ├── FareReceipt.java              # Receipt details after payment
│   ├── Ride.java                     # Ride entity with status and participants
│   ├── Rider.java                    # Rider entity with location
│   ├── User.java                     # Base user class
│   └── Vehicle.java                  # Vehicle entity with type and details
├── service/
│   ├── DriverService.java            # Business logic for driver operations
│   ├── RiderService.java             # Business logic for rider operations
│   └── RideService.java              # Business logic for ride lifecycle
├── strategy/
│   ├── CashPaymentStrategy.java      # Cash payment implementation
│   ├── DefaultFareStrategy.java      # Standard fare calculation
│   ├── LeastActiveDriverStrategy.java # Match by least completed rides
│   ├── NearestDriverStrategy.java    # Match by proximity
│   ├── PeakHourFareStrategy.java     # Peak hour fare calculation
│   └── UPIPaymentStrategy.java       # UPI payment implementation
└── util/
    ├── Constants.java                # Application constants
    ├── Helpers.java                  # Utility functions (distance calculation, etc.)
    └── IdGenerator.java              # Unique ID generation
```

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Gradle (included via wrapper)

### Building the Project
```bash
# On Windows
gradlew.bat build

# On Unix/Linux/Mac
./gradlew build
```

### Running the Application
```bash
# On Windows
gradlew.bat run

# On Unix/Linux/Mac
./gradlew run
```

## 💻 Console Menu Features

The application provides an interactive console menu with the following options:

1. **Add Rider** - Register a new rider in the system
2. **Add Driver** - Register a new driver with vehicle details
3. **View Available Drivers** - Display all currently available drivers
4. **Request Ride** - Book a ride by providing pickup and destination locations
5. **Complete Ride** - Mark a ride as completed and process payment
6. **View Rides** - Display all rides with their current status
7. **Exit** - Close the application

### Menu Implementation Highlights
- **Service Layer Only**: All operations interact exclusively through service classes
- **Input Validation**: Comprehensive error handling for invalid inputs
- **Loose Coupling**: No direct model manipulation, ensuring maintainability
- **Exception Handling**: Graceful handling of edge cases and errors

## 🔧 Key Components

### Services

#### RiderService
- `registerRider()`: Create and register new riders
- `getRiderById()`: Retrieve rider information
- Maintains in-memory list of all riders

#### DriverService
- `registerDriver()`: Create and register new drivers with vehicles
- `getAvailableDrivers()`: Fetch all available drivers
- `searchForDriver()`: Find suitable driver using matching strategy
- `setDriverAvailability()`: Update driver availability status
- `updateDriverLocation()`: Update driver's current location

#### RideService
- `requestRide()`: Create new ride request with fare calculation
- `assignDriverToRide()`: Match and assign driver to ride
- `completeRide()`: Complete ride and process payment
- `cancelRide()`: Cancel pending or assigned rides
- `showAllRides()`: Display ride history

### Strategies

#### Fare Strategies
- **DefaultFareStrategy**: `BaseFare + (Distance × PerKmRate)`
- **PeakHourFareStrategy**: `DefaultFare × PeakHourMultiplier`

#### Payment Strategies
- **CashPaymentStrategy**: Direct cash payment processing
- **UPIPaymentStrategy**: Digital payment processing

#### Ride Matching Strategies
- **NearestDriverStrategy**: Finds closest available driver using Haversine distance
- **LeastActiveDriverStrategy**: Prioritizes drivers with fewer completed rides

## 📊 Domain Models

### User (Abstract Base Class)
- `id`: Unique identifier
- `firstName`, `lastName`: User name
- `phoneNumber`: Contact information
- `accountCreatedOn`: Registration timestamp

### Rider (extends User)
- `location`: Current geographical coordinates

### Driver (extends User)
- `licenseNumber`: Driver's license
- `isAvailable`: Availability status
- `vehicle`: Associated vehicle
- `currentLocation`: Real-time position
- `completedRides`: Total completed rides count

### Ride
- `id`: Unique ride identifier
- `rider`: Associated rider
- `driver`: Assigned driver
- `startLocation`, `destination`: Route coordinates
- `rideStatus`: Current status (REQUESTED, ASSIGNED, COMPLETED, CANCELLED)
- `fare`: Calculated ride fare
- `isPaid`: Payment status
- `rideCreatedOn`, `rideEndedOn`: Timestamps

### Vehicle
- `licensePlate`: Vehicle registration number
- `vehicleType`: Type of vehicle (BIKE, AUTO, CAR)
- `model`: Vehicle details

### FareReceipt
- `rideId`: Associated ride identifier
- `amount`: Total fare amount paid
- `generatedOn`: Receipt generation timestamp
- Generated upon successful ride completion and payment


## 🎓 Learning Outcomes

This project demonstrates:
- **SOLID Principles**: Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion
- **Design Patterns**: Factory, Strategy, Service Layer
- **Clean Architecture**: Separation of concerns with distinct layers
- **Exception Handling**: Custom exceptions and error management
- **Extensibility**: Easy addition of new strategies and features

## 🔮 Future Enhancements

- Database integration for persistent storage
- REST API for mobile/web client support
- Real-time location tracking with GPS
- Rating and review system
- Ride pooling/sharing features
- Advanced matching algorithms (traffic-aware, driver ratings)
- Multiple payment gateway integrations
- Ride scheduling and prebooking
- Admin dashboard for monitoring
- Analytics and reporting features

## 📝 License

This project is developed as part of Airtribe coursework for educational purposes.

## 👥 Contributing

This is a learning project. Suggestions and improvements are welcome!

---

**Built with ❤️ using Java and Design Patterns**
