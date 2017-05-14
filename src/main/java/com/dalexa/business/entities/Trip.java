package com.dalexa.business.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Created by David
 */
@XmlRootElement
@Entity(name = "trip")
public class Trip {
    private Integer tripId;
    private boolean withEvents;
    private List<Log> logs;

    public Trip() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tripId", nullable = false)
    @XmlElement
    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    @Column(name = "withEvents", nullable = false)
    @XmlTransient
    public boolean isWithEvents() {
        return withEvents;
    }

    public void setWithEvents(boolean withEvents) {
        this.withEvents = withEvents;
    }

    //    @Basic
//    @Column(name = "startDate", nullable = false)
//    @XmlJavaTypeAdapter(type = LocalDateTime.class, value = LocalDatetimeAdapter.class)
//    public LocalDateTime getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDateTime startDate) {
//        this.startDate = startDate;
//    }
//
//    @Basic
//    @Column(name = "endDate", nullable = true)
//    @XmlJavaTypeAdapter(type = LocalDateTime.class, value = LocalDatetimeAdapter.class)
//    public LocalDateTime getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDateTime endDate) {
//        this.endDate = endDate;
//    }

    @OneToMany(mappedBy = "trip")
    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        if (tripId != trip.tripId) return false;
        return logs != null ? logs.equals(trip.logs) : trip.logs == null;
    }

    @Override
    public int hashCode() {
        int result = tripId;
        result = 31 * result + (logs != null ? logs.hashCode() : 0);
        return result;
    }
}
