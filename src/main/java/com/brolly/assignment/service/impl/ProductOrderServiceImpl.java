package com.brolly.assignment.service.impl;

import com.brolly.assignment.domain.ProductOrderRepository;
import com.brolly.assignment.domain.model.ProductOrder;
import com.brolly.assignment.domain.model.QProductOrder;
import com.brolly.assignment.service.ProductOrderService;
import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductOrderServiceImpl.class);
    private final ProductOrderRepository productOrderRepository;

    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    public void save(ProductOrder productOrder) {
        productOrderRepository.save(productOrder);
    }

    @Override
    public void save(List<ProductOrder> productOrders) {
        productOrderRepository.saveAll(productOrders);
    }

    @Override
    public List<ProductOrder> findAllByExample(ProductOrder productOrder, String start, String end) {
        QProductOrder qProductOrder = QProductOrder.productOrder;
        BooleanBuilder where = new BooleanBuilder();

        if(productOrder.getOrder_id() != null) {
            where.and(qProductOrder.order_id.eq(productOrder.getOrder_id()));
        }

        if(productOrder.getProduct_name() != null) {
            where.and(qProductOrder.product_name.equalsIgnoreCase(productOrder.getProduct_name()));
        }

        if(productOrder.getCustomer_name() != null) {
            where.and(qProductOrder.customer_name.equalsIgnoreCase(productOrder.getCustomer_name()));
        }

        if(start != null && end != null) {
            where.and(qProductOrder.order_date.between(start, end));
        }

        Iterable<ProductOrder> productOrders =
                productOrderRepository.findAll(where);

        return StreamSupport.stream(productOrders.spliterator(), false).collect(Collectors.toList());
    }
}
