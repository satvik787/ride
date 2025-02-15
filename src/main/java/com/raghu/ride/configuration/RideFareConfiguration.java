package com.raghu.ride.configuration;

import org.springframework.context.annotation.Configuration;

import com.raghu.ride.models.RideFare;
import com.raghu.ride.models.VehicleType;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

@Configuration
public class RideFareConfiguration {

    @Bean
    @Qualifier("olaCab")
    public RideFare olaCab() {
        return new RideFare(VehicleType.CAB,50, 18); // basePrice, ratePerKilometer, nightCharge, avgSpeed
    }

    @Bean
    @Qualifier("olaBike")
    public RideFare olaBike() {
        return new RideFare(VehicleType.BIKE,20, 6); // basePrice, ratePerKilometer, nightCharge, avgSpeed
    }

    @Bean
    @Qualifier("olaAuto")
    public RideFare olaAuto() {
        return new RideFare(VehicleType.AUTO,30, 10); // basePrice, ratePerKilometer, nightCharge, avgSpeed
    }

    @Bean
    @Qualifier("uberCab")
    public RideFare uberCab() {
        return new RideFare(VehicleType.CAB,55, 19); // basePrice, ratePerKilometer, nightCharge, avgSpeed
    }

    @Bean
    @Qualifier("uberBike")
    public RideFare uberBike() {
        return new RideFare(VehicleType.BIKE,25, 8); // basePrice, ratePerKilometer, nightCharge, avgSpeed
    }

    @Bean
    @Qualifier("uberAuto")
    public RideFare uberAuto() {
        return new RideFare(VehicleType.AUTO,35, 1); // basePrice, ratePerKilometer, nightCharge, avgSpeed
    }
}
