package com.brolly.assignment.integration.kafka.producer;

import com.brolly.assignment.domain.model.Export;
import com.brolly.assignment.integration.kafka.model.ExportEvent;
import com.brolly.assignment.service.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class ExportEventProducer {

    private static final Logger LOG = LoggerFactory.getLogger(ExportEventProducer.class);

    private final KafkaTemplate<String, ExportEvent> kafkaTemplate;
    private final ExportService exportService;

    @Value(value = "${app.default.topic}")
    private String topicName;

    public ExportEventProducer(KafkaTemplate<String, ExportEvent> kafkaTemplate,
                               ExportService exportService) {
        this.kafkaTemplate = kafkaTemplate;
        this.exportService = exportService;
    }

    public Long send(ExportEvent event) {
        final Long id = exportService.save(new Export("submitted"));
        event.setId(id);
        ListenableFuture<SendResult<String, ExportEvent>> future = kafkaTemplate.send(topicName, event);
        future.addCallback(new ListenableFutureCallback<SendResult<String, ExportEvent>>() {
            @Override
            public void onFailure(Throwable ex) {
                exportService.updateStatus(id, "failed");
                LOG.error("unable to send export event: {}", ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, ExportEvent> result) {
                LOG.info("export event for transaction {} sent with offset: {}", id, result.getRecordMetadata().offset());
            }
        });

        return id;
    }
}
