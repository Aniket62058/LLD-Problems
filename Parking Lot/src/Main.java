import models.ParkingLot;
import repositories.ParkingLotRepository;
import service.InitialisationService;

public class Main {
    private InitialisationService initialisationService;
    public Main() {
        this.initialisationService = new InitialisationService();
    }

    public static void main(String[] args) {
        Main main = new Main();
        ParkingLot parkingLot = main.initialisationService.initialise();
    }
}
