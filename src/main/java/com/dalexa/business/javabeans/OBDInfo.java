package com.dalexa.business.javabeans;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by David
 */
public class OBDInfo {
    private Integer id;
    private LocalDateTime time;
    private BigDecimal rpm;
    private BigDecimal speed;
    private BigDecimal throttle;
    private BigDecimal load;
    private BigDecimal fuel;
    private boolean event;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public BigDecimal getRpm() {
        return rpm;
    }

    public void setRpm(BigDecimal rpm) {
        this.rpm = rpm;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public BigDecimal getThrottle() {
        return throttle;
    }

    public void setThrottle(BigDecimal throttle) {
        this.throttle = throttle;
    }

    public BigDecimal getLoad() {
        return load;
    }

    public void setLoad(BigDecimal load) {
        this.load = load;
    }

    public BigDecimal getFuel() {
        return fuel;
    }

    public void setFuel(BigDecimal fuel) {
        this.fuel = fuel;
    }

    public boolean isEvent() {
        return event;
    }

    public void setEvent(boolean event) {
        this.event = event;
    }
}
