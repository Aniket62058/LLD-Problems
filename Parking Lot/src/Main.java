import controller.TicketController;
import dto.IssueTicketRequestDTO;
import dto.IssueTicketResponseDTO;
import models.ParkingLot;
import models.VehicleType;
import repositories.*;
import service.InitialisationService;
import service.TicketService;

public class Main {
    private InitialisationService initialisationService;
    private TicketController ticketController;
    public Main() {
        GateRepository gateRepository = new GateRepository();
        ParkingFloorRepository parkingFloorRepository = new ParkingFloorRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepository();
        TicketRepository ticketRepository = new TicketRepository();

        this.initialisationService =
                new InitialisationService(gateRepository, parkingSpotRepository, parkingFloorRepository, parkingLotRepository);
        this.ticketController= new TicketController(new TicketService(ticketRepository, parkingLotRepository, gateRepository));
    }

    public static void main(String[] args) {
        Main main = new Main();
        ParkingLot parkingLot = main.initialisationService.initialise();

        IssueTicketRequestDTO issueTicketRequestDTO =
                new IssueTicketRequestDTO(VehicleType.Four_Wheeler, "BR0127", "Black", "BMW", 1);

        IssueTicketResponseDTO issueTicketResponseDTO = main.ticketController.getTicket(issueTicketRequestDTO);

        System.out.println(issueTicketResponseDTO);
    }
}
