package com.luucasor.goldenraspberryawards.controllers;

import com.luucasor.goldenraspberryawards.dtos.LoginDTO;
import com.luucasor.goldenraspberryawards.dtos.TokenDTO;
import com.luucasor.goldenraspberryawards.exceptions.AuthException;
import com.luucasor.goldenraspberryawards.services.UserService;
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

}
