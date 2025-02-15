package com.raghu.ride.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RideDetailsDTO {

    private double totalFare;
    private VehicleType vehicleType;

    @NotNull(message = "Distance is required")
    @Positive(message = "Distance must be positive and greater than 1")
    private double distance;

    @NotNull(message = "ETA is required")
    @Positive(message = "ETA must be positive and greater than 1")
    private double eta;

    @NotNull(message = "Source is required")
    private String source;
    
    @NotNull(message = "Destination is required")
    private String destination;

    public RideDetailsDTO(VehicleType type, double totalFare, double eta) {
        this.vehicleType = type;
        this.totalFare = totalFare;
        this.eta = eta;
    }

}
