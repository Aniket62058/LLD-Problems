package models;

import java.util.Set;

public final class ElevatorStatus {
    final int id;
    final int currentFloor;
    final Direction direction;
    final DoorState doorState;
    final Mode mode;
    final boolean isOperational;
    final int load;
    final int capacity;
    final Set<Integer> upStops;   // shallow copy
    final Set<Integer> downStops;   //shallow copy

    public ElevatorStatus(int id, int currentFloor, Direction direction, DoorState doorState, Mode mode, boolean isOperational, int load, int capacity, Set<Integer> upStops, Set<Integer> downStops) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.direction = direction;
        this.doorState = doorState;
        this.mode = mode;
        this.isOperational = isOperational;
        this.load = load;
        this.capacity = capacity;
        this.upStops = upStops;
        this.downStops = downStops;
    }

    public boolean getIsOperational() {
        return isOperational;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public DoorState getDoorState() {
        return doorState;
    }

    public Mode getMode() {
        return mode;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public int getLoad() {
        return load;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<Integer> getUpStops() {
        return upStops;
    }

    public Set<Integer> getDownStops() {
        return downStops;
    }
}
