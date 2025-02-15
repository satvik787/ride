package com.raghu.ride.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.raghu.ride.models.RegisterDTO;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

        public UserEntity() {
        
        }

        public UserEntity(RegisterDTO userDTO) {
            this.firstName = userDTO.getFirstName();
            this.lastName = userDTO.getLastName();
            this.phone = userDTO.getPhone();
            this.password = userDTO.getPassword();
        }

        @OneToMany(mappedBy = "user")
        private List<RideHistoryEntity> rideHistory;

        @Id
        @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        
        @Column(name = "phone",unique = true)
        private String phone;

        @Column(name = "password")
        private String password;

}