package ru.klemtsov.watering.restserver.model;

import java.time.LocalTime;

public class Settings {

    private String ip;
    private Integer wateringPeriod;
    private LocalTime wateringStartAt;
    private Integer startWateringBelowHumidity;
    private Integer timeWateringInSec;
    private String serverIp;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getWateringPeriod() {
        return wateringPeriod;
    }

    public void setWateringPeriod(Integer wateringPeriod) {
        this.wateringPeriod = wateringPeriod;
    }

    public LocalTime getWateringStartAt() {
        return wateringStartAt;
    }

    public void setWateringStartAt(LocalTime wateringStartAt) {
        this.wateringStartAt = wateringStartAt;
    }

    public Integer getStartWateringBelowHumidity() {
        return startWateringBelowHumidity;
    }

    public void setStartWateringBelowHumidity(Integer startWateringBelowHumidity) {
        this.startWateringBelowHumidity = startWateringBelowHumidity;
    }

    public Integer getTimeWateringInSec() {
        return timeWateringInSec;
    }

    public void setTimeWateringInSec(Integer timeWateringInSec) {
        this.timeWateringInSec = timeWateringInSec;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "ip='" + ip + '\'' +
                ", wateringPeriod=" + wateringPeriod +
                ", wateringStartAt=" + wateringStartAt +
                ", startWateringBelowHumidity=" + startWateringBelowHumidity +
                ", timeWateringInSec=" + timeWateringInSec +
                ", serverIp='" + serverIp + '\'' +
                '}';
    }
}
