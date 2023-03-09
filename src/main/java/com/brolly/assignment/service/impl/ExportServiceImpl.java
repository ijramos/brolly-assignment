package com.brolly.assignment.service.impl;

import com.brolly.assignment.domain.ExportRepository;
import com.brolly.assignment.domain.model.Export;
import com.brolly.assignment.domain.model.ProductOrder;
import com.brolly.assignment.integration.kafka.model.ExportEvent;
import com.brolly.assignment.service.ExportService;
import com.brolly.assignment.service.ProductOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExportServiceImpl implements ExportService {

    private final static Logger LOG = LoggerFactory.getLogger(ExportServiceImpl.class);

    private final ExportRepository exportRepository;
    private final ProductOrderService productOrderService;

    public ExportServiceImpl(
            ExportRepository exportRepository,
            ProductOrderService productOrderService) {
        this.exportRepository = exportRepository;
        this.productOrderService = productOrderService;
    }

    @Override
    public void doOrderExport(ExportEvent exportEvent) {
        String start = null;
        String end = null;
        if(exportEvent.getFilter().getDate() != null) {
            start = exportEvent.getFilter().getDate().getStart();
            end = exportEvent.getFilter().getDate().getEnd();
        }

        List<ProductOrder> productOrders = productOrderService.findAllByExample(
                mapProductOrderFromEvent(exportEvent),
                start,
                end
        );

        // TODO: Implement File Generation
        LOG.info("-------------------------------------------");
        LOG.info("order_id, customer_name, product_name, order_date");
        for(ProductOrder order : productOrders) {
            LOG.info("{}, {}, {}, {}",
                    order.getOrder_id(),
                    order.getCustomer_name(),
                    order.getProduct_name(),
                    order.getOrder_date()
            );
        }
        LOG.info("-------------------------------------------");

        updateStatus(exportEvent.getId(), "completed");
    }

    public ProductOrder mapProductOrderFromEvent(ExportEvent exportEvent) {
        String orderId = exportEvent.getFilter().getOrder_id();
        return new ProductOrder()
                .order_id(orderId != null ? Long.valueOf(orderId) : null)
                .customer_name(exportEvent.getFilter().getCustomer_name())
                .product_name(exportEvent.getFilter().getProduct_name());
    }

    @Override
    public Export findById(Long id) {
        Optional<Export> export = exportRepository.findById(id);
        return export.orElse(null);
    }

    @Override
    public Long save(Export export) {
        return exportRepository.save(export).getId();
    }

    @Override
    public void updateStatus(Long id, String status) {
        Optional<Export> optional = exportRepository.findById(id);
        if(optional.isPresent()) {
            Export export = optional.get();
            export.setStatus(status);
            exportRepository.save(export);
        }
    }
}
