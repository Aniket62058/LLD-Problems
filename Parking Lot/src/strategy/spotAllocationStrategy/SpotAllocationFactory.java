package strategy.spotAllocationStrategy;

import repositories.ParkingLotRepository;

public class SpotAllocationFactory {
    public static SpotAllocationStrategy getSpotAllocationStrategy(ParkingLotRepository parkingLotRepository){
        return new RandomSpotAllocationStrategy(parkingLotRepository);
    }
}
