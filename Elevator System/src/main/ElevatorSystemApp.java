package main;

import concurrency.ElevatorTask;
import concurrency.RequestDispatcher;
import controlers.CollectiveScheduler;
import controlers.ElevatorController;
import controlers.NearestCarScheduler;
import controlers.Scheduler;
import exceptions.InvalidRequestException;
import models.Direction;
import models.Elevator;
import models.ElevatorStatus;
import services.ElevatorService;
import services.RequestService;
import utils.Config;
import utils.Log;

import java.util.*;

public class ElevatorSystemApp {
    public static void main(String[] args) throws Exception, InvalidRequestException {
        Config cfg = Config.defaultHighrise();
        List<Elevator> elevators = new ArrayList<>();
        for (int i = 0; i < cfg.getElevators(); i++) {
            elevators.add(new Elevator(i, cfg.getMinFloor(), cfg.getMaxFloor(), cfg.getCapacity(), (cfg.getMinFloor() + cfg.getMaxFloor())/2));
        }

        RequestDispatcher dispatcher = new RequestDispatcher();
        Scheduler scheduler = new CollectiveScheduler(); // swap with new NearestCarScheduler() to change

        ElevatorController controller = new ElevatorController(elevators, dispatcher, scheduler);

        // Start threads
        Thread ctrlThread = new Thread(controller, "controller");
        ctrlThread.start();
        List<Thread> workerThreads = new ArrayList<>();
        for (Elevator e : elevators) {
            Thread t = new Thread(new ElevatorTask(e, cfg.getMoveDelayMs(), cfg.getServiceDelayMs()), "elevator-" + e.getId());
            t.start(); workerThreads.add(t);
        }

        // API services
        RequestService requestAPI = new RequestService(dispatcher);
        ElevatorService carAPI = new ElevatorService(elevators);

        // Simulate burst of hall calls (down-peak)
        Random rnd = new Random(7);
        for (int i = 0; i < 150; i++) {
            int floor = 10 + rnd.nextInt(cfg.getMaxFloor() - 9);
            requestAPI.call(floor, Direction.DOWN);
        }

        // A few internal selections
        carAPI.pressInside(0, cfg.getMinFloor());
        carAPI.pressInside(3, 150);

        // Periodic status snapshots
        for (int i = 0; i < 5; i++) {
            Thread.sleep(500);
            System.out.println("==== Snapshot " + i + " ====");
            for (ElevatorStatus s : carAPI.status()) {
                System.out.printf("E%d f=%d dir=%s load=%d/%d mode=%s up=%s down=%s%n",
                        s.getId(), s.getCurrentFloor(), s.getDirection(), s.getLoad(), s.getCapacity(), s.getMode(), s.getUpStops(), s.getDownStops());
            }
        }

        // Demo: maintenance + strategy swap
        carAPI.setMaintenance(2, true);
        controller.setScheduler(new NearestCarScheduler());
        Log.info("Switched to NearestCar and set E2 maintenance");
    }
}
