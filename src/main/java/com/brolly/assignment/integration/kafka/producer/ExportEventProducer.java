package com.brolly.assignment.integration.kafka.producer;

import com.brolly.assignment.domain.model.Export;
import com.brolly.assignment.integration.kafka.model.ExportEvent;
import com.brolly.assignment.service.ExportService;
import com.brolly.assignment.service.util.ServiceHelperUtil;
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

    public String send(ExportEvent event) {
        if(event.getTransactionId() == null) { // NULL check assuming events for retry have transactionId generated already
            event.setTransactionId(ServiceHelperUtil.generateTransactionId());
        }
        ListenableFuture<SendResult<String, ExportEvent>> future = kafkaTemplate.send(topicName, event);
        future.addCallback(new ListenableFutureCallback<SendResult<String, ExportEvent>>() {
            @Override
            public void onFailure(Throwable ex) {
                exportService.save(new Export(event.getTransactionId(), "ForRetry")); // ForRetry status set as indicator
                // TODO: Implement a retry event sending feature
                // Option 1: Create another topic to handle events that are due for retry
                // Option 2: Create a batch service that will handle events for retry
                LOG.error("unable to send export event: {}", ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, ExportEvent> result) {
                exportService.save(new Export(event.getTransactionId(), "Submitted"));
                LOG.info("export event for transaction {} sent with offset: {}", event.getTransactionId(), result.getRecordMetadata().offset());
            }
        });

        return event.getTransactionId();
    }

}
