package com.dheeraj.kafka.springbootkafkaconsumerexample.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@JsonInclude(value= NON_NULL)
public class Network implements Serializable {
    //TopicInfo.mandatorySqlCols.cols_network=service_tag,platform,full_version_number,os,driver_id,driver_version,category
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


    public Network(String service_tag, String platform, String utc_timestamp, String full_version_number, String os, String os_service_pack, String os_type, String warranty_end_date, String driver_id, String sequence_number, String name, String driver_version, String release_date, String criticality, String pnpid, String existing_driver, String description, String type, String category, String url, String id, String filename, String format, String file_type, String size, String create_by, String update_by, boolean isNotComplete) {
        this.service_tag = service_tag;
        this.platform = platform;
        this.utc_timestamp = utc_timestamp;
        this.full_version_number = full_version_number;
        this.os = os;
        this.os_service_pack = os_service_pack;
        this.os_type = os_type;
        this.warranty_end_date = warranty_end_date;
        this.driver_id = driver_id;
        this.sequence_number = sequence_number;
        this.name = name;
        this.driver_version = driver_version;
        this.release_date = release_date;
        this.criticality = criticality;
        this.pnpid = pnpid;
        this.existing_driver = existing_driver;
        this.description = description;
        this.type = type;
        this.category = category;
        this.url = url;
        this.id = id;
        this.filename = filename;
        this.format = format;
        this.file_type = file_type;
        this.size = size;
        this.create_by = create_by;
        this.update_by = update_by;

    }

    public Network() {
    }


    public String getService_tag() {
        return service_tag;
    }

    public void setService_tag(String service_tag) {
        this.service_tag = service_tag;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUtc_timestamp() {
        return utc_timestamp;
    }

    public void setUtc_timestamp(String utc_timestamp) {
        this.utc_timestamp = utc_timestamp;
    }

    public String getFull_version_number() {
        return full_version_number;
    }

    public void setFull_version_number(String full_version_number) {
        this.full_version_number = full_version_number;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOs_service_pack() {
        return os_service_pack;
    }

    public void setOs_service_pack(String os_service_pack) {
        this.os_service_pack = os_service_pack;
    }

    public String getOs_type() {
        return os_type;
    }

    public void setOs_type(String os_type) {
        this.os_type = os_type;
    }

    public String getWarranty_end_date() {
        return warranty_end_date;
    }

    public void setWarranty_end_date(String warranty_end_date) {
        this.warranty_end_date = warranty_end_date;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getSequence_number() {
        return sequence_number;
    }

    public void setSequence_number(String sequence_number) {
        this.sequence_number = sequence_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver_version() {
        return driver_version;
    }

    public void setDriver_version(String driver_version) {
        this.driver_version = driver_version;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getCriticality() {
        return criticality;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }

    public String getPnpid() {
        return pnpid;
    }

    public void setPnpid(String pnpid) {
        this.pnpid = pnpid;
    }

    public String getExisting_driver() {
        return existing_driver;
    }

    public void setExisting_driver(String existing_driver) {
        this.existing_driver = existing_driver;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    @Override
    public String toString() {
        return "Network{" +
                "service_tag='" + service_tag + '\'' +
                ", platform='" + platform + '\'' +
                ", utc_timestamp='" + utc_timestamp + '\'' +
                ", full_version_number='" + full_version_number + '\'' +
                ", os='" + os + '\'' +
                ", os_service_pack='" + os_service_pack + '\'' +
                ", os_type='" + os_type + '\'' +
                ", warranty_end_date='" + warranty_end_date + '\'' +
                ", driver_id='" + driver_id + '\'' +
                ", sequence_number='" + sequence_number + '\'' +
                ", name='" + name + '\'' +
                ", driver_version='" + driver_version + '\'' +
                ", release_date='" + release_date + '\'' +
                ", criticality='" + criticality + '\'' +
                ", pnpid='" + pnpid + '\'' +
                ", existing_driver='" + existing_driver + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                ", filename='" + filename + '\'' +
                ", format='" + format + '\'' +
                ", file_type='" + file_type + '\'' +
                ", size='" + size + '\'' +
                ", create_by='" + create_by + '\'' +
                ", update_by='" + update_by + '\'' +
                '}';
    }
}
