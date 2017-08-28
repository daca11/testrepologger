package com.dalexa.business.entities;

import com.dalexa.business.adapters.LocalDatetimeAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by David
 */
@XmlRootElement
@Entity(name = "cameraEvent")
public class CameraEvent {
    private Integer cameraEventId;
    private Timestamp time;
    private String path;
    private Integer cameraId;
    private Trip trip;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getCameraEventId() {
        return cameraEventId;
    }

    public void setCameraEventId(Integer cameraEventId) {
        this.cameraEventId = cameraEventId;
    }

    @Basic
    @Column(name = "time")
    @XmlJavaTypeAdapter(type = LocalDateTime.class, value = LocalDatetimeAdapter.class)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "cameraId")
    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripId")
    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CameraEvent that = (CameraEvent) o;

        if (cameraEventId != null ? !cameraEventId.equals(that.cameraEventId) : that.cameraEventId != null)
            return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cameraEventId != null ? cameraEventId.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}
