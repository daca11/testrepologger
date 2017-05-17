package com.dalexa.presentation.javabean;


import java.time.LocalDateTime;

/**
 * Created by David
 */
public class TripInfo {
    private Integer id;
    private LocalDateTime firstLogTime;
    private LocalDateTime lastLogTime;
    private Boolean withEvents;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFirstLogTime() {
        return firstLogTime;
    }

    public void setFirstLogTime(LocalDateTime firstLogTime) {
        this.firstLogTime = firstLogTime;
    }

    public LocalDateTime getLastLogTime() {
        return lastLogTime;
    }

    public void setLastLogTime(LocalDateTime lastLogTime) {
        this.lastLogTime = lastLogTime;
    }

    public Boolean getWithEvents() {
        return withEvents;
    }

    public void setWithEvents(Boolean withEvents) {
        this.withEvents = withEvents;
    }
}
