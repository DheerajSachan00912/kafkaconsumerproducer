package com.dheeraj.kafka.springbootkafkaconsumerexample.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Activity_Payload {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("service_tag")
    private String service_tag;
    @JsonInclude(NON_NULL)
    private String platform;
    private String utc_timestamp;
    @JsonInclude(NON_NULL)
    private String full_version_number;
    @JsonInclude(NON_NULL)
    private String os;
    private String os_service_pack;
    private String os_type;
    private String warranty_end_date;
    @JsonInclude(NON_NULL)
    private String driver_id;
    private String sequence_number;
    private String name;
    @JsonInclude(NON_NULL)
    private String driver_version;
    private String release_date;
    private String criticality;
    private String pnpid;
    private String existing_driver;
    private String description;
    private String type;
    @JsonInclude(NON_NULL)
    private String category;
    private String url;
    private String id;
    private String filename;
    private String format;
    private String file_type;
    private String size;
    private String create_by;
    private String update_by;
    private boolean isNotComplete;
}
