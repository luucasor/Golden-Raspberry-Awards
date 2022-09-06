package com.luucasor.goldenraspberryawards.services;

import com.luucasor.goldenraspberryawards.domain.User;
import com.luucasor.goldenraspberryawards.dtos.LoginDTO;
import com.luucasor.goldenraspberryawards.dtos.TokenDTO;
import com.luucasor.goldenraspberryawards.exceptions.AuthException;
import com.luucasor.goldenraspberryawards.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class UserService implements IUserService {

    @Autowired
    TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TokenDTO loginUser(LoginDTO loginDTO) throws AuthException {
        User user = validateUser(loginDTO);
        return tokenService.generateJWTToken(user);
    }

    @Override
    public User validateUser(LoginDTO loginDTO) throws AuthException {
        User user = userRepository.findByEmail(loginDTO.getLowerCaseEmail());
        if(Objects.isNull(user) || !new BCryptPasswordEncoder().matches(loginDTO.getPassword(), user.getPassword())){
            throw new AuthException("Invalid email/password");
        }
        return user;
    }
}
