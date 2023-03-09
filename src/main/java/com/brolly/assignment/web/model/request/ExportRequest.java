package com.brolly.assignment.web.model.request;

import com.brolly.assignment.web.model.OrderFilter;

public class ExportRequest {

    private String export_format;
    private OrderFilter filter;

    public String getExport_format() {
        return export_format;
    }

    public void setExport_format(String export_format) {
        this.export_format = export_format;
    }

    public OrderFilter getFilter() {
        return filter;
    }

    public void setFilter(OrderFilter filter) {
        this.filter = filter;
    }
}
