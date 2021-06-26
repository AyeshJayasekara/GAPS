package com.ayeshj.gapstar;

import com.ayeshj.gapstar.controller.HomeController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = GapstarApplication.class)
@AutoConfigureMockMvc
class GapstarApplicationTests {

    @Autowired
    HomeController homeController;

    @Test
    void contextLoads() {
        assertThat(homeController).isNotNull();
    }

}
