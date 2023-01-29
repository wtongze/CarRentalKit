package com.wtongze.carrentalkit;

import com.wtongze.carrentalkit.model.RentalQuote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuoteControllerTest extends CarRentalKitApplicationTests {

    @Autowired
    private WebTestClient webClient;

//    @Test
//    public void basicTest() throws Exception {
//        webClient.get().uri("/quote/standard")
//                .exchange()
//                .expectStatus().isOk();
//    }
}
