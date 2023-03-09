package com.brolly.assignment.web.controller;

import com.brolly.assignment.domain.model.Export;
import com.brolly.assignment.integration.kafka.producer.ExportEventProducer;
import com.brolly.assignment.service.ExportService;
import com.brolly.assignment.web.model.mapper.ModelMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class ExportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExportService exportService;
    @MockBean
    private ExportEventProducer exportEventProducer;
    @MockBean
    private ModelMapper modelMapper;

    @Test
    void givenValidExportRequest_whenPostExport_thenReturnExportResponse() {

    }

    @Test
    void givenValidTransactionId_whenGetStatus_thenReturnExportResponse() throws Exception {
        String transactionId = "test-id";
        Export export = new Export();
        export.setStatus("submitted");
        export.setTransactionId(transactionId);

        given(exportService.findById(transactionId)).willReturn(export);

        mockMvc.perform(get("/export/test-id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("submitted"))
                .andExpect(jsonPath("$.transactionId").value("test-id"));
    }

    @Test
    void givenValidTransactionId_whenGetStatus_thenReturn404() throws Exception {
        String transactionId = "test-id";
        Export export = new Export();
        export.setStatus("submitted");
        export.setTransactionId(transactionId);

        given(exportService.findById(transactionId)).willReturn(export);

        mockMvc.perform(get("/export/test-id1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenValidTransactionId_whenGetDownload_thenReturnFile() {

    }
}