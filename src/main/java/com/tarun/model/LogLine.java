package com.tarun.model;

public class LogLine {

    private String requestTimeStamp;
    private String countryCode;
    private int responseTime;

    public LogLine(String requestTimeStamp, String countryCode, int responseTime) {
        this.requestTimeStamp = requestTimeStamp;
        this.countryCode = countryCode;
        this.responseTime = responseTime;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getResponseTime() {
        return responseTime;
    }

    @Override
    public String toString() {
        return "{" +
                "requestTimeStamp='" + requestTimeStamp + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", responseTime='" + responseTime + '\'' +
                '}';
    }
}
