package com.brolly.assignment.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long order_id;
    private String customer_name;
    private String product_name;
    private String order_date;

    public ProductOrder(String customer_name){}

    public ProductOrder() {

    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public ProductOrder order_id(Long order_id) {
        this.order_id = order_id;
        return this;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public ProductOrder customer_name(String customer_name) {
        this.customer_name = customer_name;
        return this;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public ProductOrder product_name(String product_name) {
        this.product_name = product_name;
        return this;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public ProductOrder order_date(String order_date) {
        this.order_date = order_date;
        return this;
    }
}
