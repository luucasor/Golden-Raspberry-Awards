package com.luucasor.goldenraspberryawards;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoldenRaspberryAwardsApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class GoldenRaspberryAwardsApplicationTests {

    @Test
    public void contextLoads(){

    }
}
