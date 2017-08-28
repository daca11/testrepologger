package com.dalexa.business.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;

/**
 * Created by David
 */
@XmlRootElement
@Entity
public class OBDlog { //TODO: poner floats donde toca (load y speed son ints, poner todo floats)
    private Integer obdlogId;
    private Log log;
    private BigDecimal rpm;
    private BigDecimal speed;
    private BigDecimal throttle;
    private BigDecimal load;
    private BigDecimal fuel;

    public OBDlog() {
    }

    public OBDlog(OBDlog obdLog) {
        this.log = obdLog.log;
        this.rpm = obdLog.rpm;
        this.speed = obdLog.speed;
        this.throttle = obdLog.throttle;
        this.load = obdLog.load;
        this.fuel = obdLog.fuel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "obdlogId", nullable = false)
    @XmlTransient
    public Integer getObdlogId() {
        return obdlogId;
    }

    public void setObdlogId(Integer obdlogId) {
        this.obdlogId = obdlogId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logId")
    public Log getLog() {
        return log;
    }

    public void setLog(Log logId) {
        this.log = logId;
    }

    @Basic
    @Column(name = "rpm", nullable = true)
    public BigDecimal getRpm() {
        return rpm;
    }

    public void setRpm(BigDecimal rpm) {
        this.rpm = rpm;
    }

    @Basic
    @Column(name = "speed", nullable = true)
    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    @Basic
    @Column(name = "throttle", nullable = true)
    public BigDecimal getThrottle() {
        return throttle;
    }

    public void setThrottle(BigDecimal throttle) {
        this.throttle = throttle;
    }

    @Basic
    @Column(name = "load1", nullable = true)
    public BigDecimal getLoad() {
        return load;
    }

    public void setLoad(BigDecimal load) {
        this.load = load;
    }

    @Basic
    @Column(name = "fuel", nullable = true)
    public BigDecimal getFuel() {
        return fuel;
    }

    public void setFuel(BigDecimal fuel) {
        this.fuel = fuel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OBDlog obDlog = (OBDlog) o;

        if (obdlogId != obDlog.obdlogId) return false;
        if (!log.equals(obDlog.log)) return false;
        if (rpm != null ? !rpm.equals(obDlog.rpm) : obDlog.rpm != null) return false;
        if (speed != null ? !speed.equals(obDlog.speed) : obDlog.speed != null) return false;
        if (throttle != null ? !throttle.equals(obDlog.throttle) : obDlog.throttle != null) return false;
        if (load != null ? !load.equals(obDlog.load) : obDlog.load != null) return false;
        return fuel != null ? fuel.equals(obDlog.fuel) : obDlog.fuel == null;
    }

    @Override
    public int hashCode() {
        int result = obdlogId;
        result = 31 * result + log.hashCode();
        result = 31 * result + (rpm != null ? rpm.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + (throttle != null ? throttle.hashCode() : 0);
        result = 31 * result + (load != null ? load.hashCode() : 0);
        result = 31 * result + (fuel != null ? fuel.hashCode() : 0);
        return result;
    }
}
