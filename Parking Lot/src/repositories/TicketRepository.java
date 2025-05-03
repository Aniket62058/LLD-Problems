package repositories;

import exceptions.TicketNotFoundException;
import models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    Map<Integer, Ticket> ticketMap;

    public TicketRepository() {
        this.ticketMap = new HashMap<>();
    }

    public Ticket get(int TicketId){
        Ticket Ticket = ticketMap.get(TicketId);
        if(Ticket == null){
            throw new TicketNotFoundException("Ticket not found for : " + TicketId);
        }
        return Ticket;
    }

    public void put(Ticket Ticket){
        ticketMap.put(Ticket.getId(), Ticket);
    }
}
