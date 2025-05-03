package service;

import models.*;
import repositories.GateRepository;
import repositories.ParkingFloorRepository;
import repositories.ParkingLotRepository;
import repositories.ParkingSpotRepository;

import java.util.ArrayList;
import java.util.List;

public class InitialisationService {
    private GateRepository gateRepository;
    private ParkingSpotRepository parkingSpotRepository;
    private ParkingFloorRepository parkingFloorRepository;
    private ParkingLotRepository parkingLotRepository;

    public InitialisationService(GateRepository gateRepository, ParkingSpotRepository parkingSpotRepository, ParkingFloorRepository parkingFloorRepository, ParkingLotRepository parkingLotRepository) {
        this.gateRepository = gateRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingFloorRepository = parkingFloorRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    /***
     *  This will create a parking lot with 10 floors, and each floor having 10 spots
     *  @return ParkingLot object
     */
    public ParkingLot initialise(){
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        parkingLot.setStatus(Status.ACTIVE);
        parkingLot.setAddress("Road a, Bengaluru");
        parkingLot.setCapacity(100);

        Gate entryGate = new Gate();
        entryGate.setGateNumber(1);
        entryGate.setGateType(GateType.ENTRY);
        entryGate.setOperator("Aniket Kumar");
        entryGate.setStatus(Status.ACTIVE);
        entryGate.setId(1);
        entryGate.setFloorNumber(1);
        entryGate.setParkingLotId(1);

        Gate exitGate = new Gate();
        exitGate.setGateNumber(2);
        exitGate.setGateType(GateType.EXIT);
        exitGate.setOperator("Aniket Shandilya");
        exitGate.setStatus(Status.ACTIVE);
        exitGate.setId(2);
        exitGate.setFloorNumber(1);
        exitGate.setParkingLotId(1);

        // set gates for parkingLot
        parkingLot.setGates(List.of(entryGate, exitGate));
        gateRepository.put(entryGate);
        gateRepository.put(exitGate);

        List<ParkingFloor> parkingFloors = new ArrayList<>();

        for(int i=1;i<=10;i++){
            List<ParkingSpot> parkingSpots = new ArrayList<>();
            ParkingFloor parkingFloor = new ParkingFloor();
            parkingFloor.setId(100 + i);
            parkingFloor.setStatus(Status.ACTIVE);
            parkingFloor.setFloorNumber(i);
            for(int j=1;j<=10;j++){
                ParkingSpot parkingSpot = new ParkingSpot();
                parkingSpot.setId(1000+j);
                parkingSpot.setNumber(i*100+j);
                if(j%2==0){
                    parkingSpot.setSupportedVehicleType(VehicleType.Two_Wheeler);
                } else {
                    parkingSpot.setSupportedVehicleType(VehicleType.Four_Wheeler);
                }
                parkingSpot.setStatus(Status.AVAILABLE);
                parkingSpots.add(parkingSpot);
                parkingSpotRepository.put(parkingSpot);
            }
            parkingFloor.setParkingSpots(parkingSpots);
            parkingFloorRepository.put(parkingFloor);
            parkingFloors.add(parkingFloor);
        }

        parkingLot.setParkingFloors(parkingFloors);
        parkingLotRepository.put(parkingLot);
        return parkingLot;
    }
}
