package controlers;

import concurrency.RequestDispatcher;
import models.CallRequest;
import models.Elevator;
import models.ElevatorStatus;
import models.Mode;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController implements Runnable{
    private final List<Elevator> elevators;
    private final RequestDispatcher dispatcher;
    private volatile Scheduler scheduler;

    public ElevatorController(List<Elevator> elevators, RequestDispatcher dispatcher, Scheduler scheduler) {
        this.elevators = elevators;
        this.dispatcher = dispatcher;
        this.scheduler = scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    List<ElevatorStatus> snapshotAll() {
        List<ElevatorStatus> elevatorStatuses = new ArrayList<>();
        for(Elevator elevator : elevators) {
            elevatorStatuses.add(elevator.snapshot());
        }
        return elevatorStatuses;
    }

    @Override
    public void run() {
        try {
            while (true) {
                CallRequest callRequest = dispatcher.take();    //block until available
                int carId = scheduler.select(snapshotAll(), callRequest);
                if(carId >= 0) {
                    Elevator elevator = elevators.get(carId);
                    ElevatorStatus elevatorStatus = elevator.snapshot();
                    if(elevatorStatus.getMode() == Mode.NORMAL && elevatorStatus.getIsOperational() && elevatorStatus.getLoad() < elevatorStatus.getCapacity()) {
                        elevator.addHallStop(callRequest.getFloor(), callRequest.getDirection());
                        dispatcher.markAssigned(callRequest);
                    } else {
                        dispatcher.defer(callRequest);
                        Thread.sleep(25);
                    }
                } else {
                    dispatcher.defer(callRequest);
                    Thread.sleep(50);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
