package com.brolly.assignment;

import com.brolly.assignment.domain.model.ProductOrder;
import com.brolly.assignment.service.ProductOrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductOrderService productOrderService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<ProductOrder>> typeReference = new TypeReference<List<ProductOrder>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data.json");

            try {
                List<ProductOrder> productOrders = mapper.readValue(inputStream, typeReference);
                productOrderService.save(productOrders);
                LOG.info("product_orders initialized!");
            } catch(IOException e) {
                LOG.info("unable to initialze product_orders: " + e.getMessage());
            }
        };
    }
}
