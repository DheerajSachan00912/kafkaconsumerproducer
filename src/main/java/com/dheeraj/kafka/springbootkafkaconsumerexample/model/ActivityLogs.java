package com.dheeraj.kafka.springbootkafkaconsumerexample.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityLogs implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lineno;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String datetime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String logentry;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String level;

    public ActivityLogs() {
    }

    public ActivityLogs(String lineNo, String dateTime, String logEntry, String level) {
        this.lineno = lineNo;
        this.datetime = dateTime;
        this.logentry = logEntry;
        this.level = level;
    }

    public String getLineno() {
        return lineno;
    }

    public void setLineno(String lineno) {
        this.lineno = lineno;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getLogentry() {
        return logentry;
    }

    public void setLogentry(String logentry) {
        this.logentry = logentry;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "ActivityLogs{" +
                "LineNo='" + lineno + '\'' +
                ", DateTime='" + datetime + '\'' +
                ", LogEntry='" + logentry + '\'' +
                ", Level='" + level + '\'' +
                '}';
    }
}
