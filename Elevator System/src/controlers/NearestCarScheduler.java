package controlers;

import models.CallRequest;
import models.Direction;
import models.ElevatorStatus;
import models.Mode;

import java.util.List;

public class NearestCarScheduler implements Scheduler{
    @Override
    public int select(List<ElevatorStatus> statuses, CallRequest callRequest) {
        int best = -1, bestScore = Integer.MAX_VALUE;
        for(ElevatorStatus elevatorStatus: statuses) {
            if(!elevatorStatus.getIsOperational() || elevatorStatus.getMode() != Mode.NORMAL || elevatorStatus.getLoad() > elevatorStatus.getCapacity()) continue;
            int dist = Math.abs(elevatorStatus.getCurrentFloor() - callRequest.getFloor());
            int dirPenalty = (elevatorStatus.getDirection() == Direction.IDLE || elevatorStatus.getDirection() == callRequest.getDirection()) ? 0 : 50;
            int score = dist * 2 + dirPenalty;
            if(score < bestScore) {
                bestScore = score;
                best = elevatorStatus.getId();
            }
        }

        return best;
    }
}
