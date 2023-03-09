package com.brolly.assignment.web.controller;

import com.brolly.assignment.domain.model.Export;
import com.brolly.assignment.integration.kafka.producer.ExportEventProducer;
import com.brolly.assignment.service.ExportService;
import com.brolly.assignment.web.model.mapper.ModelMapper;
import com.brolly.assignment.web.model.request.ExportRequest;
import com.brolly.assignment.web.model.response.ExportResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(value = "/export")
public class ExportController {

    private static final Logger LOG = LoggerFactory.getLogger(ExportController.class);

    private final ExportService exportService;
    private final ExportEventProducer exportEventProducer;
    private final ModelMapper modelMapper;

    public ExportController(
            ExportService exportService,
            ExportEventProducer exportEventProducer,
            ModelMapper modelMapper) {
        this.exportService = exportService;
        this.exportEventProducer = exportEventProducer;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<EntityModel<ExportResponse>> export(@RequestBody ExportRequest exportRequest) {
        String transactionId = exportEventProducer.send(modelMapper.mapExportRequestToEventModel(exportRequest));

        ExportResponse response = new ExportResponse();
        response.setStatus("Submitted");
        response.setTransactionId(transactionId);

        EntityModel<ExportResponse> entityModel = EntityModel.of(response,
                linkTo(methodOn(ExportController.class).status(response.getTransactionId())).withSelfRel());

        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    @GetMapping(value = "/{transactionId}")
    public ResponseEntity<EntityModel<ExportResponse>> status(@PathVariable String transactionId) {
        Export export = exportService.findById(transactionId);

        if(export == null) {
            return ResponseEntity.notFound().build();
        }

        ExportResponse response = new ExportResponse();
        response.setStatus(export.getStatus());
        response.setTransactionId(transactionId);

        EntityModel<ExportResponse> entityModel = EntityModel.of(response,
                linkTo(methodOn(ExportController.class).status(transactionId)).withSelfRel());

        if("Completed".equals(export.getStatus())) {
            entityModel.add(linkTo(methodOn(ExportController.class).download(transactionId)).withRel("download"));
        }

        return ResponseEntity.ok().body(entityModel);
    }

    @GetMapping(value = "/{transactionId}/download")
    public ResponseEntity<?> download(@PathVariable String transactionId) {
        // TODO: Implement service call to enable file download
        return ResponseEntity.notFound().build();
    }
}