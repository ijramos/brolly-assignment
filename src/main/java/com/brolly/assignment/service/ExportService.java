package com.brolly.assignment.service;

import com.brolly.assignment.domain.model.Export;
import com.brolly.assignment.integration.kafka.model.ExportEvent;

public interface ExportService {

    public void doOrderExport(ExportEvent exportEvent);
    public Export findById(String transactionId);
    public String save(Export export);
    public void updateStatus(String transactionId, String status);

}
