package com.brolly.assignment.integration.kafka.model;

public class ExportFilter {

    private String order_id;
    private String product_name;
    private String customer_name;
    private DateFilter date;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public ExportFilter order_id(String order_id) {
        this.order_id = order_id;
        return this;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public ExportFilter product_name(String product_name) {
        this.product_name = product_name;
        return this;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public ExportFilter customer_name(String customer_name) {
        this.customer_name = customer_name;
        return this;
    }

    public DateFilter getDate() {
        return date;
    }

    public void setDate(DateFilter date) {
        this.date = date;
    }

    public ExportFilter date(DateFilter date) {
        this.date = date;
        return this;
    }
}
