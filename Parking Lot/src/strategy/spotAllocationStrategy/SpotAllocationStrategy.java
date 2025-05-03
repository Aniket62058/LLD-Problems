package strategy.spotAllocationStrategy;

import models.Gate;
import models.ParkingSpot;
import models.VehicleType;

public interface SpotAllocationStrategy {
    ParkingSpot getSpot(VehicleType vehicleType, Gate gate);
}
