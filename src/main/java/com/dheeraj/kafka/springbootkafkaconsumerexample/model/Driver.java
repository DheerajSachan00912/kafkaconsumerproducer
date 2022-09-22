package com.dheeraj.kafka.springbootkafkaconsumerexample.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Driver implements Serializable {
    //TopicInfo.mandatorySqlCols.cols_driver= LogType,Version,Mode,ServiceTag,Entitlement,OptimizationsAvailable,Details.ID,ActivityLogs.LogEntry
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String logtype;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String version;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mode;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String starttime;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String endtime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String servicetag;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String userconsent;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String eula;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String entitlement;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String registered;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private Result result;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private List<Details> detailslist;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private List<ActivityLogs> activitylogslist;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String create_by;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String update_by;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String isoptimizations_available;





    public Driver() {
    }

    public Driver(String logtype, String version, String mode, String starttime, String endtime, String servicetag, String userconsent, String eula, String entitlement, String registered, Result result, List<Details> detailslist, List<ActivityLogs> activitylogslist, String create_by, String update_by, String isoptimizations_available) {
        this.logtype = logtype;
        this.version = version;
        this.mode = mode;
        this.starttime = starttime;
        this.endtime = endtime;
        this.servicetag = servicetag;
        this.userconsent = userconsent;
        this.eula = eula;
        this.entitlement = entitlement;
        this.registered = registered;
        this.result = result;
        this.detailslist = detailslist;
        this.activitylogslist = activitylogslist;
        this.create_by = create_by;
        this.update_by = update_by;
        this.isoptimizations_available = isoptimizations_available;

    }

    public String getLogtype() {
        return logtype;
    }

    public void setLogtype(String logtype) {
        this.logtype = logtype;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getServicetag() {
        return servicetag;
    }

    public void setServicetag(String servicetag) {
        this.servicetag = servicetag;
    }

    public String getUserconsent() {
        return userconsent;
    }

    public void setUserconsent(String userconsent) {
        this.userconsent = userconsent;
    }

    public String getEula() {
        return eula;
    }

    public void setEula(String eula) {
        this.eula = eula;
    }

    public String getEntitlement() {
        return entitlement;
    }

    public void setEntitlement(String entitlement) {
        this.entitlement = entitlement;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<Details> getDetailslist() {
        return detailslist;
    }

    public void setDetailslist(List<Details> detailslist) {
        this.detailslist = detailslist;
    }

    public List<ActivityLogs> getActivitylogslist() {
        return activitylogslist;
    }

    public void setActivitylogslist(List<ActivityLogs> activitylogslist) {
        this.activitylogslist = activitylogslist;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getIsoptimizations_available() {
        return isoptimizations_available;
    }

    public void setIsoptimizations_available(String isoptimizations_available) {
        this.isoptimizations_available = isoptimizations_available;
    }



    @Override
    public String toString() {
        return "Driver{" +
                "logtype='" + logtype + '\'' +
                ", version='" + version + '\'' +
                ", mode='" + mode + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", servicetag='" + servicetag + '\'' +
                ", userconsent='" + userconsent + '\'' +
                ", eula='" + eula + '\'' +
                ", entitlement='" + entitlement + '\'' +
                ", registered='" + registered + '\'' +
                ", result=" + result +
                ", detailslist=" + detailslist +
                ", activitylogslist=" + activitylogslist +
                ", create_by='" + create_by + '\'' +
                ", update_by='" + update_by + '\'' +
                ", isoptimizations_available='" + isoptimizations_available + '\'' +
                '}';
    }
}

