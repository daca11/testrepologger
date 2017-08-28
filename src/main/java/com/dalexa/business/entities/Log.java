package com.dalexa.business.entities;

import com.dalexa.business.adapters.LocalDatetimeAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

/**
 * Created by David
 */
@XmlRootElement
@Entity(name = "log")
public class Log {
    private Integer logId;
    private LocalDateTime time;
    private GPSlog gpsLog;
    private OBDlog obdLog;
    private Trip trip;
    private boolean event;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "time", nullable = false)
    @XmlJavaTypeAdapter(type = LocalDateTime.class, value = LocalDatetimeAdapter.class)
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "log", cascade = CascadeType.ALL)
    @XmlElement(name = "gps")
    public GPSlog getGpsLog() {
        return gpsLog;
    }

    public void setGpsLog(GPSlog gpsLog) {
        this.gpsLog = gpsLog;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "log", cascade = CascadeType.ALL)
    @XmlElement(name = "obd")
    public OBDlog getObdLog() {
        return obdLog;
    }

    public void setObdLog(OBDlog obdLog) {
        this.obdLog = obdLog;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripId")
    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    @Basic
    @Column(name = "event", nullable = false)
    public boolean isEvent() {
        return event;
    }

    public void setEvent(boolean event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (logId != log.logId) return false;
        if (!time.equals(log.time)) return false;
        if (gpsLog != null ? !gpsLog.equals(log.gpsLog) : log.gpsLog != null) return false;
        if (obdLog != null ? !obdLog.equals(log.obdLog) : log.obdLog != null) return false;
        return trip.equals(log.trip);
    }

    @Override
    public int hashCode() {
        int result = logId;
        result = 31 * result + time.hashCode();
        result = 31 * result + (gpsLog != null ? gpsLog.hashCode() : 0);
        result = 31 * result + (obdLog != null ? obdLog.hashCode() : 0);
        result = 31 * result + trip.hashCode();
        return result;
    }
}
