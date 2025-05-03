package strategy.feeCalculationStrategy;

import models.Ticket;
import models.VehicleType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LowFeeCalculationStrategy implements FeeCalculationStrategy{
    private static final double PER_MIN_RATE_2_WHEELER = 2;
    private static final double PER_MIN_RATE_4_WHEELER = 5;

    @Override
    public double getFeeAmount(Ticket ticket) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime currentTime = LocalDateTime.now();
        long numberOfMinutes = ChronoUnit.MINUTES.between(currentTime, entryTime);

        if(ticket.getVehicle().getVehicleType().equals(VehicleType.Two_Wheeler)){
            return numberOfMinutes * PER_MIN_RATE_2_WHEELER;
        }
        else{
            return numberOfMinutes * PER_MIN_RATE_4_WHEELER;
        }
    }
}
