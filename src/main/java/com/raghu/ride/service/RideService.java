package com.raghu.ride.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.raghu.ride.entity.RideDetailsEntity;
import com.raghu.ride.entity.RideHistoryEntity;
import com.raghu.ride.models.RideDTO;
import com.raghu.ride.models.RideDetailsDTO;
import com.raghu.ride.repository.RideHistoryRepository;
import com.raghu.ride.repository.UserRepository;

@Service
public class RideService {

    private final UberService uberService;
    private final OlaService olaService;
    private final RideHistoryRepository rideHistoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public RideService(UberService uberService, OlaService olaService, RideHistoryRepository rideHistoryRepository, UserRepository userRepository) {
        this.uberService = uberService;
        this.rideHistoryRepository = rideHistoryRepository;
        this.olaService = olaService;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<RideDTO> getRideDetails(RideDetailsDTO rideDetails,Long userId) {
        RideDTO uberRide = uberService.getRideDetails(rideDetails.getDistance(), rideDetails.getEta());
        RideDTO olaRide = olaService.getRideDetails(rideDetails.getDistance(), rideDetails.getEta());
        
        List<RideDetailsEntity> rideDetailsEntities = new ArrayList<>();
        
        RideHistoryEntity rideHistoryEntity =  new RideHistoryEntity(userRepository.getReferenceById(userId),rideDetails.getSource(), rideDetails.getDestination(), rideDetails.getDistance());
        uberRide.getRides().forEach(ride -> rideDetailsEntities.add(new RideDetailsEntity(rideHistoryEntity, ride.getVehicleType(), ride.getTotalFare(), ride.getEta(), "Uber")));
        olaRide.getRides().forEach(ride -> rideDetailsEntities.add(new RideDetailsEntity(rideHistoryEntity, ride.getVehicleType(), ride.getTotalFare(), ride.getEta(), "Ola")));

        rideHistoryEntity.rideDetails = rideDetailsEntities;
        this.rideHistoryRepository.save(rideHistoryEntity);
        return List.of(uberRide, olaRide);
    }

    public List<RideHistoryEntity> getRideHistory(long userId) {
        return rideHistoryRepository.findByUserId(userId);
    }

}
