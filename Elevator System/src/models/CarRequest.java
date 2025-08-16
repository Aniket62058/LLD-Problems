package models;

public final class CarRequest {
    final int floor;
    final long timestamp;

    CarRequest(int floor) {
        this.floor = floor;
        this.timestamp = System.nanoTime();
    }

    public int getFloor() {
        return floor;
    }
}
