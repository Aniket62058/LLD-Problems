package repositories;

import exceptions.ParkingSpotNotFoundException;
import models.ParkingSpot;

import java.util.HashMap;
import java.util.Map;

public class ParkingSpotRepository {
    Map<Integer, ParkingSpot> ParkingSpotMap;

    public ParkingSpotRepository() {
        this.ParkingSpotMap = new HashMap<>();
    }

    public ParkingSpot get(int parkingSpotId){
        ParkingSpot ParkingSpot = ParkingSpotMap.get(parkingSpotId);
        if(ParkingSpot == null){
            throw new ParkingSpotNotFoundException("Parking spot not found for : " + parkingSpotId);
        }
        return ParkingSpot;
    }

    public void put(ParkingSpot ParkingSpot){
        ParkingSpotMap.put(ParkingSpot.getId(), ParkingSpot);
        System.out.println("Parking floor has been added successfully");
    }
}
