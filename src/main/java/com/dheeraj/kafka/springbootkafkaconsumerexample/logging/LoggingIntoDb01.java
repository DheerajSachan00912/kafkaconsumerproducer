package com.dheeraj.kafka.springbootkafkaconsumerexample.logging;



import com.dheeraj.kafka.springbootkafkaconsumerexample.model.Network;
import com.dheeraj.kafka.springbootkafkaconsumerexample.model.Driver;

import javax.persistence.*;


@Entity
@Table(name = "LoggingIntoDb01")
public class LoggingIntoDb01 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Transient
    private Network network;
    @Transient
    private Driver driver;

    private String logs;
    private String timeStamp;

    public LoggingIntoDb01() {
    }

    public LoggingIntoDb01(String logs, String timeStamp) {
        this.logs = logs;
        this.timeStamp = timeStamp;
    }

    public LoggingIntoDb01(Long id, String logs, String timeStamp) {
        this.id = id;
        this.logs = logs;
        this.timeStamp = timeStamp;
    }

    public Network getNetwork() {
        return network;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }


    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoggingIntoDb{" +
                "id=" + id +
                ", logs='" + logs + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
