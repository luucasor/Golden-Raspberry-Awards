package com.luucasor.goldenraspberryawards.controllers;

import com.luucasor.goldenraspberryawards.dtos.TimeGapDTO;
import com.luucasor.goldenraspberryawards.services.ProducerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/producer")
public class ProducerController {

    @Autowired
    ProducerService producerService;

    @GetMapping("/minAndMaxAwardTimeGap")
    @ApiOperation(value = "Get the producer with the longest gap between two consecutive awards", response = TimeGapDTO.class)
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "OK", response = TimeGapDTO.class),
                    @ApiResponse(code = 403, message = "Forbidden")
            }
    )
    public TimeGapDTO getMinAndMaxAwardTimeGap(){
        return producerService.getMinAndMaxAwardTimeGap();
    }

}
