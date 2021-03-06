package com.dalexa.presentation.controllers;

import com.dalexa.business.dao.TripRetriever;
import com.dalexa.business.entities.Log;
import com.dalexa.presentation.javabean.TripInfo;
import com.jsf2leaf.model.Map;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David
 */
@Named
@ViewScoped
public class IndexController implements Serializable {
    private List<TripInfo> trips;
    private List<Log> logs;
    private Map map;

    @Inject
    private TripRetriever tripRetriever;

    @PostConstruct
    public void initialize(){
        trips = new ArrayList<>();
        logs = null;

        List<Integer> tripIds = tripRetriever.getTripIds(); //TODO: with events?

        for (Integer tripId : tripIds) {
            TripInfo trip = new TripInfo();
            trip.setId(tripId);
            Object[] minMaxDates = tripRetriever.getTripDates(tripId);
            trip.setFirstLogTime((LocalDateTime) minMaxDates[0]);
            trip.setLastLogTime((LocalDateTime) minMaxDates[1]);
            trip.setWithEvents(tripRetriever.hasEvents(tripId));

            trips.add(trip);
        }
    }



    public List<TripInfo> getTrips() {
        return trips;
    }

    public void setTrips(List<TripInfo> trips) {
        this.trips = trips;
    }
}
