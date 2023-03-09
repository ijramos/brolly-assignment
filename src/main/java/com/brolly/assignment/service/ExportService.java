package com.brolly.assignment.service;

import com.brolly.assignment.domain.model.Export;
import com.brolly.assignment.integration.kafka.model.ExportEvent;

public interface ExportService {

    public void doOrderExport(ExportEvent exportEvent);
    public Export findById(Long id);
    public Long save(Export export);
    public void updateStatus(Long id, String status);

}
