package models;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Elevator {
    final int id;
    final int minFloor;
    final int maxFloor;
    final int capacity;

    final AtomicInteger currentFloor = new AtomicInteger(0);
    final AtomicReference<Direction> direction = new AtomicReference<>(Direction.IDLE);
    final AtomicReference<DoorState> door = new AtomicReference<>(DoorState.CLOSED);
    final AtomicReference<Mode> mode = new AtomicReference<>(Mode.NORMAL);
    final AtomicBoolean operational = new AtomicBoolean(true);
    final AtomicInteger load = new AtomicInteger(0);


    // Directional stop sets (heaps) guarded by a private lock to minimize contention
    private final PriorityQueue<Integer> upStops = new PriorityQueue<>();
    private final PriorityQueue<Integer> downStops = new PriorityQueue<>(Comparator.reverseOrder());
    private final Object stopsLock = new Object();

    // Internal car button queue
    final BlockingQueue<CarRequest> internalQueue = new LinkedBlockingQueue<>();

    public Elevator(int id, int minFloor, int maxFloor, int capacity, int startFloor) {
        this.id = id;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.capacity = capacity;
        this.currentFloor.set(startFloor);
    }

    public void addHallStop(int floor, Direction reqDir) {
        if(mode.get() != Mode.NORMAL) return;
        synchronized (stopsLock) {
            if(reqDir == Direction.UP) upStops.add(floor);
            else downStops.add(floor);

            if(direction.get() == Direction.IDLE) {
                if(floor > currentFloor.get()) direction.set(Direction.UP);
                else if(floor < currentFloor.get()) direction.set(Direction.DOWN);
            }
        }
    }

    public void setMaintenance(boolean on) {
        mode.set(on ? Mode.MAINTENANCE : Mode.NORMAL);
        if (on) clearAllStops();
    }

    public void triggerEmergencyTo(int floor) {
        mode.set(Mode.EMERGENCY);
        clearAllStops();
        synchronized (stopsLock) {
            if (floor >= currentFloor.get()) upStops.add(floor); else downStops.add(floor);
            direction.set(floor >= currentFloor.get() ? Direction.UP : Direction.DOWN);
        }
    }

    private void clearAllStops() { synchronized (stopsLock) { upStops.clear(); downStops.clear(); } }

    public void addCarStop(int floor) {
        internalQueue.offer(new CarRequest(floor));
    }

    public boolean isFull() {
        return load.get() >= capacity;
    }

    public ElevatorStatus snapshot() {
        synchronized (stopsLock) {
            return new ElevatorStatus(
                    id, currentFloor.get(), direction.get(), door.get(), mode.get(),
                    operational.get(), load.get(), capacity,
                    new HashSet<>(upStops), new HashSet<>(downStops)
            );
        }
    }

    // Package-private helpers used by ElevatorTask
    public Integer peekUp() {
        synchronized (stopsLock) {
            return upStops.peek();
        }
    }

    public Integer peekDown() {
        synchronized (stopsLock) {
            return downStops.peek();
        }
    }

    public Integer pollUp() {
        synchronized (stopsLock) {
            return upStops.poll();
        }
    }

    public Integer pollDown() {
        synchronized (stopsLock) {
            return downStops.poll();
        }
    }

    public boolean hasUp() {
        synchronized (stopsLock) {
            return !upStops.isEmpty();
        }
    }

    public boolean hasDown() {
        synchronized (stopsLock) {
            return !downStops.isEmpty();
        }
    }


    public int getId() {
        return id;
    }

    public int getMinFloor() {
        return minFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public int getCapacity() {
        return capacity;
    }

    public AtomicInteger getCurrentFloor() {
        return currentFloor;
    }

    public AtomicReference<Direction> getDirection() {
        return direction;
    }

    public AtomicReference<DoorState> getDoor() {
        return door;
    }

    public AtomicReference<Mode> getMode() {
        return mode;
    }

    public AtomicBoolean getOperational() {
        return operational;
    }

    public AtomicInteger getLoad() {
        return load;
    }

    public PriorityQueue<Integer> getUpStops() {
        return upStops;
    }

    public PriorityQueue<Integer> getDownStops() {
        return downStops;
    }

    public Object getStopsLock() {
        return stopsLock;
    }

    public BlockingQueue<CarRequest> getInternalQueue() {
        return internalQueue;
    }
}
