package com.luucasor.goldenraspberryawards.services;

import com.luucasor.goldenraspberryawards.domain.User;
import com.luucasor.goldenraspberryawards.dtos.LoginDTO;
import com.luucasor.goldenraspberryawards.dtos.TokenDTO;

import javax.security.auth.message.AuthException;

public interface IUserService {

    User validateUser(LoginDTO loginDTO) throws AuthException;
    TokenDTO loginUser(LoginDTO loginDTO) throws AuthException;

}
