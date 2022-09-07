package com.luucasor.goldenraspberryawards.controllers;

import com.luucasor.goldenraspberryawards.dtos.TimeGapDTO;
import com.luucasor.goldenraspberryawards.dtos.TokenDTO;
import com.luucasor.goldenraspberryawards.services.ProducerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/producer")
public class ProducerController {

    @Autowired
    ProducerService producerService;

    @GetMapping("/longestAwardTimeGap")
    @ApiOperation(value = "Get the producer with the longest gap between two consecutive awards", response = TimeGapDTO.class)
    public TimeGapDTO getlongestAwardTimeGap(){
        //TODO Falta criar as consultas
        //TODO Falta criar validations
        return producerService.getlongestAwardTimeGap();
    }

}
