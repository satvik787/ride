package com.raghu.ride.service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.raghu.ride.entity.UserEntity;
import com.raghu.ride.exception.ClientException;
import com.raghu.ride.models.LoginDTO;
import com.raghu.ride.models.RegisterDTO;
import com.raghu.ride.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserByID(Long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
        if(!user.isPresent()) {
            throw new ClientException("User not found",HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    public UserEntity register(RegisterDTO userDTO) {
        UserEntity user = new UserEntity(userDTO);
        Optional<UserEntity> existingUser = this.userRepository.findByPhone(user.getPhone());
        if(existingUser.isPresent()) {
            throw new ClientException("User already exists with phone number: " + user.getPhone(),HttpStatus.CONFLICT);
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        this.userRepository.save(user);
        return user;
    }

    public UserEntity login(LoginDTO userDTO) {
        Optional<UserEntity> existingUser = this.userRepository.findByPhone(userDTO.getPhone());
        if(!existingUser.isPresent()) {
            throw new ClientException("User does not exist with phone number: " + userDTO.getPhone(),HttpStatus.NOT_FOUND);
        }
        if(!BCrypt.checkpw(userDTO.getPassword(), existingUser.get().getPassword())) {
            throw new ClientException("Invalid password",HttpStatus.UNAUTHORIZED);
        }
        return existingUser.get();
    }

}
