package dto;

import models.VehicleType;

public class IssueTicketRequestDTO {
    private VehicleType vehicleType;
    private String vehicleNumber;
    private String VehicleColor;
    private String vehicleMake;
    private int gateId;

    public IssueTicketRequestDTO() {
    }

    public IssueTicketRequestDTO(VehicleType vehicleType, String vehicleNumber, String vehicleColor, String vehicleMake, int gateId) {
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        VehicleColor = vehicleColor;
        this.vehicleMake = vehicleMake;
        this.gateId = gateId;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleColor() {
        return VehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        VehicleColor = vehicleColor;
    }

    public int getGateId() {
        return gateId;
    }

    public void setGateId(int gateId) {
        this.gateId = gateId;
    }
}
