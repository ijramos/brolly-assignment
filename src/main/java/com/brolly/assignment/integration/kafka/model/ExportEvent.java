package com.brolly.assignment.integration.kafka.model;

public class ExportEvent {

    private String transactionId;
    private String format;
    private ExportFilter filter;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public ExportEvent transactionId(String transactionId) {
        this.transactionId = transactionId;
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
