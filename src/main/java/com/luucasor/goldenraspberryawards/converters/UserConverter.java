package com.luucasor.goldenraspberryawards.converters;

import com.luucasor.goldenraspberryawards.domain.User;
import com.luucasor.goldenraspberryawards.dtos.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserConverter {

    public User dtoToEntity(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder(16).encode(userDTO.getPassword()));
        return user;
    }

    public UserDTO entityToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
