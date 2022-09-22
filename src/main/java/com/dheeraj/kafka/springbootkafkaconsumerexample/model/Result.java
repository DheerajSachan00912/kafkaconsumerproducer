package com.dheeraj.kafka.springbootkafkaconsumerexample.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result implements Serializable {

    private String optimizationsAvailable;
    private String optimizationsDone;
    private String finalResult;

    public Result() {
    }

    public Result(String optimizationsAvailable, String optimizationsDone, String finalResult) {
        this.optimizationsAvailable = optimizationsAvailable;
        this.optimizationsDone = optimizationsDone;
        this.finalResult = finalResult;
    }

    public String getOptimizationsAvailable() {
        return optimizationsAvailable;
    }

    public void setOptimizationsAvailable(String optimizationsAvailable) {
        this.optimizationsAvailable = optimizationsAvailable;
    }

    public String getOptimizationsDone() {
        return optimizationsDone;
    }

    public void setOptimizationsDone(String optimizationsDone) {
        this.optimizationsDone = optimizationsDone;
    }

    public String getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    @Override
    public String toString() {
        return "Result{" +
                "OptimizationsAvailable='" + optimizationsAvailable + '\'' +
                ", OptimizationsDone='" + optimizationsDone + '\'' +
                ", FinalResult='" + finalResult + '\'' +
                '}';
    }
}
