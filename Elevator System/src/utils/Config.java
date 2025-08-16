package utils;

public final class Config {
    final int minFloor;
    final int maxFloor;
    final int elevators;
    final int capacity;
    final int moveDelayMs;
    final int serviceDelayMs;

    public Config(int minFloor, int maxFloor, int elevators, int capacity, int moveDelayMs, int serviceDelayMs) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.elevators = elevators;
        this.capacity = capacity;
        this.moveDelayMs = moveDelayMs;
        this.serviceDelayMs = serviceDelayMs;
    }

    public static Config defaultHighrise() {
        return new Config(0, 200, 10, 12, 15, 60);
    }

    public int getMinFloor() {
        return minFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public int getElevators() {
        return elevators;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMoveDelayMs() {
        return moveDelayMs;
    }

    public int getServiceDelayMs() {
        return serviceDelayMs;
    }
}
