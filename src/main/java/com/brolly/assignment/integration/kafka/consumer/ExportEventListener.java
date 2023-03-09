package com.brolly.assignment.integration.kafka.consumer;

import com.brolly.assignment.integration.kafka.model.ExportEvent;
import com.brolly.assignment.service.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public class ExportEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(ExportEventListener.class);

    private final ExportService exportService;

    public ExportEventListener(ExportService exportService) {
        this.exportService = exportService;
    }

    @KafkaListener(topics = "${app.default.topic}", groupId = "${app.default.group}")
    public void receive(@Payload List<ExportEvent> events) {
        for(ExportEvent event : events) {
            exportService.doOrderExport(event);
        }
    }
}
