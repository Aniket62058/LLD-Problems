package repositories;

import exceptions.GateNotFoundException;
import models.Gate;

import java.util.HashMap;
import java.util.Map;

public class GateRepository {
    Map<Integer, Gate> GateMap;

    public GateRepository() {
        this.GateMap = new HashMap<>();
    }

    public Gate get(int gateId){
        Gate Gate = GateMap.get(gateId);
        if(Gate == null){
            throw new GateNotFoundException("Gate not found for : " + gateId);
        }
        return Gate;
    }

    public void put(Gate gate){
        GateMap.put(gate.getId(), gate);
    }
}
