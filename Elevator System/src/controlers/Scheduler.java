package controlers;

import models.CallRequest;
import models.ElevatorStatus;

import java.util.List;

public interface Scheduler {
    int select(List<ElevatorStatus> statuses, CallRequest callRequest);
}
