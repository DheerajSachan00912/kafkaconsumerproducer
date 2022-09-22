package com.dheeraj.kafka.springbootkafkaconsumerexample.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Details_Payload {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String logType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String version;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mode;
    private String startTime;
    private String endTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String serviceTag;
    private String userConsent;
    private String eula;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String entitlement;
    private String registered;
    private Result result;
    private List<Details> detailsList;
    private List<ActivityLogs> activityLogsList;
    private String create_by;
    private String update_by;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String optimizationsAvailable;
    private boolean isNotComplete;
}
