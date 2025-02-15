package com.raghu.ride.models;

import java.util.List;

import lombok.Data;

@Data
public class RideDTO {
    private String provider;
    private List<RideDetailsDTO> rides;
}
