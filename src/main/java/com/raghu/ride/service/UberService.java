package com.raghu.ride.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.raghu.ride.models.RideFare;
import com.raghu.ride.models.VehicleType;
import com.raghu.ride.models.RideDTO;
import com.raghu.ride.models.RideDetailsDTO;

import java.util.ArrayList;

@Service
public class UberService {

    private RideFare uberCab;
    private RideFare uberBike;
    private RideFare uberAuto;

    @Autowired
    public UberService(@Qualifier("uberCab") RideFare uberCab, 
                      @Qualifier("uberBike") RideFare uberBike, 
                      @Qualifier("uberAuto") RideFare uberAuto) {
        this.uberCab = uberCab;
        this.uberBike = uberBike;
        this.uberAuto = uberAuto;
    }

    public RideDTO getRideDetails(double distance, double time) {
        RideDTO rideDTO = new RideDTO();
        rideDTO.setProvider("Uber");
        ArrayList<RideDetailsDTO> rides = new ArrayList<>();
        rides.add(new RideDetailsDTO(VehicleType.CAB, uberCab.calculateFare(distance), uberCab.calculateETA(time)));
        rides.add(new RideDetailsDTO(VehicleType.BIKE, uberBike.calculateFare(distance), uberBike.calculateETA(time)));
        rides.add(new RideDetailsDTO(VehicleType.AUTO, uberAuto.calculateFare(distance), uberAuto.calculateETA(time)));
        rideDTO.setRides(rides);
        return rideDTO;
    }
}
