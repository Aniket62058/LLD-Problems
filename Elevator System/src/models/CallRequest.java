package models;

public final class CallRequest {
    final int floor;
    final Direction direction;
    final long timestamp;

    public CallRequest(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
        this.timestamp = System.nanoTime();
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
