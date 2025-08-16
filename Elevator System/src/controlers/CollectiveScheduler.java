package controlers;

import models.CallRequest;
import models.Direction;
import models.ElevatorStatus;
import models.Mode;

import java.util.List;

public class CollectiveScheduler implements Scheduler{
    @Override
    public int select(List<ElevatorStatus> statuses, CallRequest callRequest) {
        int best = -1, bestScore = Integer.MAX_VALUE;
        for(ElevatorStatus elevatorStatus: statuses) {
            if(!elevatorStatus.getIsOperational() || elevatorStatus.getMode() != Mode.NORMAL || elevatorStatus.getLoad() >= elevatorStatus.getCapacity()) continue;
            int dist = Math.abs(elevatorStatus.getCurrentFloor() - callRequest.getFloor());
            int score = 100000;
            if(elevatorStatus.getDirection() == callRequest.getDirection()) {
                boolean onTheWay = (elevatorStatus.getDirection() == Direction.UP && elevatorStatus.getCurrentFloor() <= callRequest.getFloor())
                        || (elevatorStatus.getDirection() == Direction.DOWN && elevatorStatus.getCurrentFloor() >= callRequest.getFloor());
                score = onTheWay ? dist : dist + 200;
            } else if(elevatorStatus.getDirection() == Direction.IDLE) {
                score = dist + 20;
            } else {
                score = dist + 400;
            }

            if(score < bestScore) {
                bestScore = score;
                best = elevatorStatus.getId();
            }
        }
        return best;
    }
}
