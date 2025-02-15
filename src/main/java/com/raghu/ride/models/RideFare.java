package com.raghu.ride.models;

import java.time.LocalTime;

import lombok.Data;



@Data
public class RideFare {
    private double basePrice;
    private double ratePerKilometer;
    private VehicleType vehicleType;

    public RideFare(VehicleType vehicleType,double basePrice, double ratePerKilometer) {
        this.vehicleType = vehicleType;
        this.basePrice = basePrice;
        this.ratePerKilometer = ratePerKilometer;
    }

    public double calculateETA(double timeByMap) {
        double etaMultiplier,trafficMultiplier;
        switch (vehicleType) {
            case BIKE:
            etaMultiplier = 1.01;
            trafficMultiplier = 1.01; 
            break;
            case AUTO:
            etaMultiplier = 1.026; 
            trafficMultiplier = 1.015; 
            break;
            case CAB:
            etaMultiplier = 1.04; 
            trafficMultiplier = 1.02; 
            break;
            default:
            trafficMultiplier = 1.0; // default case, should not happen
            etaMultiplier = 1.0; // default case, should not happen
        }
        
        return timeByMap * etaMultiplier * trafficMultiplier;
    
    }

    public double calculateFare(double distance) {
        LocalTime now = LocalTime.now();
        boolean isNight = now.isAfter(LocalTime.of(22, 0)) || now.isBefore(LocalTime.of(6, 0));

        double totalFare = basePrice + (ratePerKilometer * distance);

        if (isNight) {
            totalFare += totalFare * 0.015; // 1.5% night charge
        }

        return Math.round(totalFare * 100.0) / 100.0;
    }
}
