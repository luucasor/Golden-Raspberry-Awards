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
        TimeGapDTO minMax = TimeGapDTOBuilder.getMinMax();
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/producer/longestAwardTimeGap"))
                .andExpect(status().isOk()).andReturn();

        TimeGapDTO timeGapDTO = new ObjectMapper().readValue(result.getResponse().getContentAsString(), TimeGapDTO.class);

        AwardDTO firstMinDTO = minMax.getMin().get(0);
        AwardDTO firstMinBuilder = timeGapDTO.getMin().get(0);
        Assert.assertEquals(firstMinDTO.getProducer(), firstMinBuilder.getProducer());
        Assert.assertEquals(firstMinDTO.getInterval(), firstMinBuilder.getInterval());
        Assert.assertEquals(firstMinDTO.getFollowingWin(), firstMinBuilder.getFollowingWin());
        Assert.assertEquals(firstMinDTO.getPreviousWin(), firstMinBuilder.getPreviousWin());

        AwardDTO secondMinDTO = minMax.getMin().get(1);
        AwardDTO secondMinBuilder = timeGapDTO.getMin().get(1);
        Assert.assertEquals(secondMinDTO.getProducer(), secondMinBuilder.getProducer());
        Assert.assertEquals(secondMinDTO.getInterval(), secondMinBuilder.getInterval());
        Assert.assertEquals(secondMinDTO.getFollowingWin(), secondMinBuilder.getFollowingWin());
        Assert.assertEquals(secondMinDTO.getPreviousWin(), secondMinBuilder.getPreviousWin());

        AwardDTO firstMaxDTO = minMax.getMax().get(0);
        AwardDTO firstMaxBuilder = timeGapDTO.getMax().get(0);
        Assert.assertEquals(firstMaxDTO.getProducer(), firstMaxBuilder.getProducer());
        Assert.assertEquals(firstMaxDTO.getInterval(), firstMaxBuilder.getInterval());
        Assert.assertEquals(firstMaxDTO.getFollowingWin(), firstMaxBuilder.getFollowingWin());
        Assert.assertEquals(firstMaxDTO.getPreviousWin(), firstMaxBuilder.getPreviousWin());

        AwardDTO secondMaxDTO = minMax.getMax().get(1);
        AwardDTO secondMaxBuilder = timeGapDTO.getMax().get(1);
        Assert.assertEquals(secondMaxDTO.getProducer(), secondMaxBuilder.getProducer());
        Assert.assertEquals(secondMaxDTO.getInterval(), secondMaxBuilder.getInterval());
        Assert.assertEquals(secondMaxDTO.getFollowingWin(), secondMaxBuilder.getFollowingWin());
        Assert.assertEquals(secondMaxDTO.getPreviousWin(), secondMaxBuilder.getPreviousWin());

    }

}
