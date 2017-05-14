package com.dalexa.business.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by David
 */
@XmlRootElement
@Entity
public class GPSlog {
    private Integer gpslogId;
    private Log log;
    private Integer latitude;
    private Integer longitude;

    public GPSlog() {
    }

    public GPSlog(GPSlog gpsLog) {
        this.log = gpsLog.log;
        this.latitude = gpsLog.latitude;
        this.longitude = gpsLog.longitude;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gpslogId", nullable = false)
    @XmlTransient
    public Integer getGpslogId() {
        return gpslogId;
    }

    public void setGpslogId(Integer gpslogId) {
        this.gpslogId = gpslogId;
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
    @Column(name = "latitude", nullable = true)
    @XmlElement(name = "lat")
    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longitude", nullable = true)
    @XmlElement(name = "lng")
    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GPSlog gpSlog = (GPSlog) o;

        if (gpslogId != gpSlog.gpslogId) return false;
        if (!log.equals(gpSlog.log)) return false;
        if (latitude != null ? !latitude.equals(gpSlog.latitude) : gpSlog.latitude != null) return false;
        return longitude != null ? longitude.equals(gpSlog.longitude) : gpSlog.longitude == null;
    }

    @Override
    public int hashCode() {
        int result = gpslogId;
        result = 31 * result + log.hashCode();
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}
