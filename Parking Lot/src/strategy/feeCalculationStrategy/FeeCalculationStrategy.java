package strategy.feeCalculationStrategy;

import models.Ticket;

public interface FeeCalculationStrategy {
    double getFeeAmount(Ticket ticket);
}
