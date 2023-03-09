package com.brolly.assignment.integration.kafka.model;

public class DateFilter {

    private String start;
    private String end;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public DateFilter start(String start) {
        this.start = start;
        return this;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public DateFilter end(String end) {
        this.end = end;
        return this;
    }
}
