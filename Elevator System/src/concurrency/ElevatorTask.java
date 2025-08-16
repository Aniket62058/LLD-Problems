package concurrency;

import models.*;

public class ElevatorTask implements Runnable{
    private final Elevator elevator;
    private final int moveDelayMs;  // travel per floor
    private final int serviceDelaysMs;  // door open dwell

    public ElevatorTask(Elevator elevator, int moveDelayMs, int serviceDelaysMs) {
        this.elevator = elevator;
        this.moveDelayMs = moveDelayMs;
        this.serviceDelaysMs = serviceDelaysMs;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if(elevator.getMode().get() == Mode.MAINTENANCE) {
                    elevator.getDirection().set(Direction.IDLE);
                    Thread.sleep(50);
                    continue;
                }

                // Fold internal car requests into directional queues
                CarRequest carRequest = elevator.getInternalQueue().poll();
                if(carRequest != null) {
                    if(carRequest.getFloor() > elevator.getCurrentFloor().get())  {
                        elevator.addHallStop(carRequest.getFloor(), Direction.UP);
                    } else if (carRequest.getFloor() < elevator.getCurrentFloor().get()) {
                        elevator.addHallStop(carRequest.getFloor(), Direction.DOWN);
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private enum StepDecision {
        MOVE, SERVICE, WAIT
    }

    private StepDecision nextStep() {
        // pick target by direction; switch if necessary
        Integer target = null;
        if(elevator.getDirection().get() == Direction.UP && elevator.hasUp()) {
            target = elevator.peekUp();
        } else if(elevator.getDirection().get() == Direction.DOWN && elevator.hasDown()) {
            target = elevator.peekDown();
        } else {
            if(elevator.hasUp()) {
                elevator.getDirection().set(Direction.UP);
                target = elevator.peekUp();
            } else if(elevator.hasDown()) {
                elevator.getDirection().set(Direction.DOWN);
                target = elevator.peekDown();
            } else {
                elevator.getDirection().set(Direction.IDLE);
                return StepDecision.WAIT;
            }
        }
        
        int here = elevator.getCurrentFloor().get();
        if(target == here) {
            if(elevator.getDirection().get() == Direction.UP) elevator.pollUp();
            else if (elevator.getDirection().get() == Direction.DOWN) elevator.pollDown();
            return StepDecision.SERVICE;
        }
        return StepDecision.MOVE;
    }

    private void openDoors() {
        elevator.getDoor().set(DoorState.OPEN);
    }

    private void closeDoors() {
        elevator.getDoor().set(DoorState.CLOSED);
    }

    private void simulateFlow() {
        int exiting = 1;    // pretend someone exits
        int entering = 1;   // pretend someone enters
        elevator.getLoad().updateAndGet(x -> Math.max(0, x - exiting));
        elevator.getLoad().updateAndGet(x -> Math.min(elevator.getCapacity(), x + entering));
    }
}
