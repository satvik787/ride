package com.raghu.ride.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.raghu.ride.models.VehicleType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "ride_details")
public class RideDetailsEntity {

    public RideDetailsEntity() {
    }

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    public Long id;
    public VehicleType vehicleType;
    public double totalFare;
    public double eta;
    public String provider;


    @ManyToOne
    @JoinColumn(name = "ride_history_id")
    @JsonIgnore
    public RideHistoryEntity rideHistory;
    
    public RideDetailsEntity(RideHistoryEntity rideHistory,VehicleType vehicleType, double totalFare, double eta, String provider) {
        this.rideHistory = rideHistory;
        this.vehicleType = vehicleType;
        this.totalFare = totalFare;
        this.eta = eta;
        this.provider = provider;
    }

}
