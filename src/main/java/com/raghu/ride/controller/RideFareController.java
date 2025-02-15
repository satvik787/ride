package com.raghu.ride.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raghu.ride.aop.LoginRequired;
import com.raghu.ride.entity.RideHistoryEntity;
import com.raghu.ride.models.ResponseDTO;
import com.raghu.ride.models.RideDTO;
import com.raghu.ride.models.RideDetailsDTO;
import com.raghu.ride.service.RideService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class RideFareController {

    private final RideService rideService;
    
    @Autowired
    public RideFareController(RideService rideService) {
        this.rideService = rideService;
    }

    @LoginRequired
    @PostMapping("/fare")
    public ResponseEntity<ResponseDTO<List<RideDTO>>> getFare(@Validated @RequestBody RideDetailsDTO rideDetails, BindingResult result,HttpServletRequest request) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body(ResponseDTO.<List<RideDTO>>error(result));
        }
        Long userId = (Long) request.getAttribute("userId");
        List<RideDTO> rides = rideService.getRideDetails(rideDetails,userId);

        return ResponseEntity.ok().body(ResponseDTO.<List<RideDTO>>success("Fare calculated successfully", rides));
    }

    @LoginRequired
    @GetMapping("/history")
    public ResponseEntity<ResponseDTO<List<RideHistoryEntity>>> getHistory(HttpServletRequest request) {
        long userId = (Long) request.getAttribute("userId");
        List<RideHistoryEntity> rides = rideService.getRideHistory(userId);
        return ResponseEntity.ok(ResponseDTO.<List<RideHistoryEntity>>success("", rides));
    }

}
