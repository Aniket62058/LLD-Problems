package services;

import models.Elevator;
import models.ElevatorStatus;

import java.util.ArrayList;
import java.util.List;

public class ElevatorService {
    private final List<Elevator> elevators;

    public ElevatorService(List<Elevator> elevators) { this.elevators = elevators; }

    public void pressInside(int elevatorId, int floor) { elevators.get(elevatorId).addCarStop(floor); }
    public void setMaintenance(int elevatorId, boolean on) { elevators.get(elevatorId).setMaintenance(on); }
    public void emergencyRecall(int lobbyFloor) { elevators.forEach(e -> e.triggerEmergencyTo(lobbyFloor)); }
    public List<ElevatorStatus> status() { List<ElevatorStatus> r = new ArrayList<>(); elevators.forEach(e -> r.add(e.snapshot())); return r; }
}
