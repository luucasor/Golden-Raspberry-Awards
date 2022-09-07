package com.luucasor.goldenraspberryawards.controllers;

import com.luucasor.goldenraspberryawards.dtos.LoginDTO;
import com.luucasor.goldenraspberryawards.dtos.TokenDTO;
import com.luucasor.goldenraspberryawards.dtos.UserDTO;
import com.luucasor.goldenraspberryawards.exceptions.AuthException;
import com.luucasor.goldenraspberryawards.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping
    @ApiOperation(value = "Performs the login of the user passed by parameter", response = TokenDTO.class)
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 401, message = "Unauthorized"),
                    @ApiResponse(code = 500, message = "Internal Server Error"),
            }
    )
    public TokenDTO loginUser(@RequestBody LoginDTO loginDTO) throws AuthException {
        return userService.loginUser(loginDTO);
    }

    @PostMapping("/register")
    @ApiOperation(value = "Register a new user", response = TokenDTO.class)
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 409, message = "Conflict"),
                    @ApiResponse(code = 500, message = "Internal Server Error"),
            }
    )
    public TokenDTO registerUser(@RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
    }

}
