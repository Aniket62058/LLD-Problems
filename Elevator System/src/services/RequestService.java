package services;

import concurrency.RequestDispatcher;
import exceptions.InvalidRequestException;
import models.CallRequest;
import models.Direction;

public class RequestService {
    private final RequestDispatcher dispatcher;


    public RequestService(RequestDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void call(int floor, Direction direction) throws InvalidRequestException {
        if(floor < 0) throw new InvalidRequestException("floor cannot be negative");
        dispatcher.submit(new CallRequest(floor, direction));
    }
}
