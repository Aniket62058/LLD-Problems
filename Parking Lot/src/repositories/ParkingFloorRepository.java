package repositories;

import models.ParkingFloor;

import java.util.HashMap;
import java.util.Map;

public class ParkingFloorRepository {
    Map<Integer, ParkingFloor> ParkingFloorMap;

    public ParkingFloorRepository(Map<Integer, ParkingFloor> ParkingFloorMap) {
        this.ParkingFloorMap = new HashMap<>();
    }

    public ParkingFloor get(int parkingFloorId){
        ParkingFloor ParkingFloor = ParkingFloorMap.get(parkingFloorId);
        if(ParkingFloor == null){
            // TODO : throw exception
        }
        return ParkingFloor;
    }

    public void put(ParkingFloor parkingFloor){
        ParkingFloorMap.put(parkingFloor.getId(), parkingFloor);
        System.out.println("Parking floor has been added successfully");
    }

}
