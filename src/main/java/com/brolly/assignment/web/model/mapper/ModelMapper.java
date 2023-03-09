package com.brolly.assignment.web.model.mapper;

import com.brolly.assignment.integration.kafka.model.DateFilter;
import com.brolly.assignment.integration.kafka.model.ExportEvent;
import com.brolly.assignment.integration.kafka.model.ExportFilter;
import com.brolly.assignment.web.model.OrderFilter;
import com.brolly.assignment.web.model.request.ExportRequest;

public class ModelMapper {

    public ExportEvent mapExportRequestToEventModel(ExportRequest request) {
        OrderFilter filter = request.getFilter();
        ExportFilter export = new ExportFilter();
        if(filter != null) {
            export
                    .order_id(filter.getOrder_id())
                    .customer_name(filter.getCustomer_name())
                    .product_name(filter.getProduct_name());

            if(filter.getDate() != null) {
                DateFilter date = new DateFilter()
                        .start(filter.getDate().getStart())
                        .end(filter.getDate().getEnd());
                export.date(date);
            }
        }

        return new ExportEvent()
                .format(request.getExport_format())
                .filter(export);
    }

}
