package com.brolly.assignment.integration.kafka.producer;

import com.brolly.assignment.integration.kafka.model.ExportEvent;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class ExportEventProducerTest {
    @Autowired
    private ExportEventProducer eventProducer;


    @Test
    void send() {
        ExportEvent event = new ExportEvent();
        event.setTransactionId("test-id");
        eventProducer.send(event);

        // TODO: Assert if event was successfully sent
    }
}