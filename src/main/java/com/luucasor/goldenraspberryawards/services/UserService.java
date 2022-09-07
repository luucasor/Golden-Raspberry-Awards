package com.luucasor.goldenraspberryawards.services;

import com.luucasor.goldenraspberryawards.converters.UserConverter;
import com.luucasor.goldenraspberryawards.domain.User;
import com.luucasor.goldenraspberryawards.dtos.LoginDTO;
import com.luucasor.goldenraspberryawards.dtos.TokenDTO;
import com.luucasor.goldenraspberryawards.dtos.UserDTO;
import com.luucasor.goldenraspberryawards.exceptions.AuthException;
import com.luucasor.goldenraspberryawards.exceptions.ConflictException;
import com.luucasor.goldenraspberryawards.exceptions.FieldFormatException;
import com.luucasor.goldenraspberryawards.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

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

    public TokenDTO registerUser(UserDTO userDTO) {
            Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
            Pattern passwordPattern = Pattern.compile("(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
            String emailLowerCase = userDTO.getLowerCaseEmail();

            if(!emailPattern.matcher(emailLowerCase).matches()){
                throw new FieldFormatException("Invalid email format");
            }
            Long count = userRepository.countByEmail(emailLowerCase);
            if(count > 0L){
                throw new ConflictException("Email already in use");
            }
            if(!passwordPattern.matcher(userDTO.getPassword()).matches()){
                List<String> content = Arrays.asList(
                        "The password length must be greater than or equal to 8",
                        "The password must contain one or more uppercase characters",
                        "The password must contain one or more lowercase characters",
                        "The password must contain one or more numeric values",
                        "The password must contain one or more special characters");
                throw new FieldFormatException(content.toString());
            }
            User user = userRepository.save(new UserConverter().dtoToEntity(userDTO));



        if(user != null){
            return tokenService.generateJWTToken(user);
        }
        return null;
    }
}
