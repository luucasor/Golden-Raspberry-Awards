package com.luucasor.goldenraspberryawards.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luucasor.goldenraspberryawards.GoldenRaspberryAwardsApplicationTests;
import com.luucasor.goldenraspberryawards.controllers.AuthController;
import com.luucasor.goldenraspberryawards.dtos.LoginDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AuthControllerTest extends GoldenRaspberryAwardsApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private AuthController authController;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void performValidUserLogin_shouldReturn200StatusCode() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("luucasor@gmail.com");
        loginDTO.setPassword("Qwe##123");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(loginDTO);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isOk()).andReturn();

        Assert.assertTrue(result.getResponse().getContentAsString().contains("{\"token\":\"eyJhbGciOiJIUzI1NiJ9."));
    }

    @Test
    public void performInvalidUserLogin_shouldReturn401StatusCode() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("invalid");
        loginDTO.setPassword("Qwe##123");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(loginDTO);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isUnauthorized());
    }
}
