package com.brolly.assignment.domain;

import com.brolly.assignment.domain.model.ProductOrder;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends
        CrudRepository<ProductOrder, Long>, QuerydslPredicateExecutor<ProductOrder> {
}
