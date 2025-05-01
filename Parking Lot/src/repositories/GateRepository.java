package repositories;

import models.Gate;

import java.util.HashMap;
import java.util.Map;

public class GateRepository {
    Map<Integer, Gate> GateMap;

    public GateRepository(Map<Integer, Gate> GateMap) {
        this.GateMap = new HashMap<>();
    }

    public Gate get(int gateId){
        Gate Gate = GateMap.get(gateId);
        if(Gate == null){
            // TODO : throw exception
        }
        return Gate;
    }

    public void put(Gate gate){
        GateMap.put(gate.getId(), gate);
        System.out.println("Parking floor has been added successfully");
    }
}
