package com.luucasor.goldenraspberryawards.controllers;

import com.luucasor.goldenraspberryawards.dtos.TimeGapDTO;
import com.luucasor.goldenraspberryawards.services.ProducerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/producer")
public class ProducerController {

    @Autowired
    ProducerService producerService;

    @GetMapping("/longestAwardTimeGap")
    @ApiOperation(value = "Get the producer with the longest gap between two consecutive awards", response = TimeGapDTO.class)
    //public TimeGapDTO getlongestAwardTimeGap(){
    public TimeGapDTO getlongestAwardTimeGap(){
        //TODO Falta criar as consultas
        //TODO Falta criar validations
        return producerService.getlongestAwardTimeGap();
    }

}
