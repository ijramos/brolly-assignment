package com.brolly.assignment.service;

import com.brolly.assignment.domain.model.ProductOrder;

import java.util.List;
import java.util.function.Predicate;

public interface ProductOrderService {

    public List<ProductOrder> findAllByExample(ProductOrder productOrder, String start, String end);
    public void save(ProductOrder productOrder);
    public void save(List<ProductOrder> productOrders);

}
