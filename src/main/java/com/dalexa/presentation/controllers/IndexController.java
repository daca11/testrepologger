package com.dalexa.presentation.controllers;

import com.dalexa.business.dao.TripRetriever;
import com.dalexa.business.entities.Log;
import com.dalexa.presentation.javabean.TripInfo;
import com.jsf2leaf.model.*;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

            trips.add(trip);
        }
    }

    public void selectTrip(TripInfo selectedTrip){
        this.logs = tripRetriever.getLogs(selectedTrip.getId());
        this.map = null;
        Layer markerLayer = new Layer();
        Layer polyLayer = new Layer();
        Polyline polyLine = new Polyline();

        for (Log log : logs) {
            if (log.getGpsLog() != null) {
                LatLong position = new LatLong(log.getGpsLog().getLatitude().toString(),
                        log.getGpsLog().getLongitude().toString());

                markerLayer.addMarker(new Marker(position));
                polyLine.addPoint(position);
            }
        }

        polyLayer.addPolyline(polyLine);

        //TODO: associate a marker with a obd log, change colour, click a marker
        //TODO: responsive (javascript on resize)
        //TODO: refresh map!!!

        if (!logs.isEmpty()){
            Optional<Log> first = logs.stream().filter(l -> l.getGpsLog() != null).findFirst();
            first.ifPresent(log ->
                    this.map = new Map().setWidth("578px").setHeight("360px").setZoom(13)
                    .setCenter(
                            new LatLong(
                                    log.getGpsLog().getLatitude().toString(),
                                    log.getGpsLog().getLongitude().toString()
                            )
                    )
                    .addLayer(markerLayer).addLayer(polyLayer)
            );
        }
    }

    public List<TripInfo> getTrips() {
        return trips;
    }

    public void setTrips(List<TripInfo> trips) {
        this.trips = trips;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
