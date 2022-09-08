package com.luucasor.goldenraspberryawards.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luucasor.goldenraspberryawards.GoldenRaspberryAwardsApplicationTests;
import com.luucasor.goldenraspberryawards.builders.TimeGapDTOBuilder;
import com.luucasor.goldenraspberryawards.controllers.ProducerController;
import com.luucasor.goldenraspberryawards.dtos.AwardDTO;
import com.luucasor.goldenraspberryawards.dtos.TimeGapDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class ProducerControllerTest extends GoldenRaspberryAwardsApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private ProducerController producerController;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(producerController).build();
    }

    @Test
    public void performTheSearchForThePrizeWithTheLongestAndShortestTimeInterval_shouldReturn200StatusCode() throws Exception {
        TimeGapDTO minMaxBuilder = TimeGapDTOBuilder.getMinMax();
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/producer/minAndMaxAwardTimeGap"))
                .andExpect(status().isOk()).andReturn();

        TimeGapDTO timeGapDTO = new ObjectMapper().readValue(result.getResponse().getContentAsString(), TimeGapDTO.class);

        Assert.assertEquals(1, timeGapDTO.getMin().size());
        Assert.assertEquals(1, timeGapDTO.getMax().size());

        AwardDTO firstMinBuilder = minMaxBuilder.getMin().get(0);
        AwardDTO firstMinDTO = timeGapDTO.getMin().get(0);
        Assert.assertEquals(firstMinBuilder.getProducer(), firstMinDTO.getProducer());
        Assert.assertEquals(firstMinBuilder.getInterval(), firstMinDTO.getInterval());
        Assert.assertEquals(firstMinBuilder.getFollowingWin(), firstMinDTO.getFollowingWin());
        Assert.assertEquals(firstMinBuilder.getPreviousWin(), firstMinDTO.getPreviousWin());

        AwardDTO firstMaxBuilder = minMaxBuilder.getMax().get(0);
        AwardDTO firstMaxDTO = timeGapDTO.getMax().get(0);
        Assert.assertEquals(firstMaxBuilder.getProducer(), firstMaxDTO.getProducer());
        Assert.assertEquals(firstMaxBuilder.getInterval(), firstMaxDTO.getInterval());
        Assert.assertEquals(firstMaxBuilder.getFollowingWin(), firstMaxDTO.getFollowingWin());
        Assert.assertEquals(firstMaxBuilder.getPreviousWin(), firstMaxDTO.getPreviousWin());

    }

}
