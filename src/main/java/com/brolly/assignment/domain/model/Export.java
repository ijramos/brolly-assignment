package com.brolly.assignment.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Export {

    @Id
    private String transactionId;
    private String path;
    private String status;

    public Export() {}

    public Export(String transactionId, String status) {
        this.transactionId = transactionId;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
