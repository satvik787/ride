package com.raghu.ride.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ride_history")
public class RideHistoryEntity {

    
    public RideHistoryEntity() {
        this.requestTime = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    public Long id;
    public String source;
    public String destination;
    public double distance;
    public Timestamp requestTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    public UserEntity user;

    @OneToMany(mappedBy = "rideHistory", cascade = jakarta.persistence.CascadeType.PERSIST, orphanRemoval = true)
    public List<RideDetailsEntity> rideDetails;
    
    public RideHistoryEntity(UserEntity user,String source,String destination, double distance){
        this.source = source;
        this.user = user;
        this.destination = destination;
        this.distance = distance;
        this.requestTime = new Timestamp(System.currentTimeMillis());
    }
    
}
