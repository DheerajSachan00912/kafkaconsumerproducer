package com.dheeraj.kafka.springbootkafkaconsumerexample.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Details implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    private String description;

    public Details() {
    }

    public Details(String iD, String description) {
        this.id = iD;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Details{" +
                "iD='" + id + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }
}
