package concurrency;

import models.CallRequest;
import models.Direction;

import java.util.EnumSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class RequestDispatcher {
    private final BlockingQueue<CallRequest> externalQueue = new LinkedBlockingQueue<>();
    private final ConcurrentHashMap<Integer, EnumSet<Direction>> pendingByFloor = new ConcurrentHashMap<>();

    public void submit(CallRequest callRequest) {
        pendingByFloor.compute(callRequest.getFloor(), (floor, set) -> {
            if(set == null) set = EnumSet.noneOf(Direction.class);
            if(!set.contains(callRequest.getDirection())) {
                set.add(callRequest.getDirection());
                externalQueue.offer(callRequest);
            }
            return set;
        });
    }

    public CallRequest take() throws InterruptedException {
        return externalQueue.take();
    }

    public void markAssigned(CallRequest callRequest) {
        pendingByFloor.computeIfPresent(callRequest.getFloor(), (floor, set) -> {
            set.remove(callRequest.getDirection());
            return set.isEmpty() ? null : set;
        });
    }

    public void defer(CallRequest callRequest) {
        externalQueue.offer(callRequest);
    }
}
