package com.brolly.assignment.integration.kafka.model;

public class ExportEvent {

    private Long id;
    private String format;
    private ExportFilter filter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExportEvent id(Long id) {
        this.id = id;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public ExportEvent format(String format) {
        this.format = format;
        return this;
    }

    public ExportFilter getFilter() {
        return filter;
    }

    public void setFilter(ExportFilter filter) {
        this.filter = filter;
    }

    public ExportEvent filter(ExportFilter filter) {
        this.filter = filter;
        return this;
    }
}
