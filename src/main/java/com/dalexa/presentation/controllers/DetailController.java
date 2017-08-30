package com.dalexa.presentation.controllers;

import com.dalexa.business.dao.TripRetriever;
import com.dalexa.business.entities.CameraEvent;
import com.dalexa.business.entities.GPSlog;
import com.dalexa.business.javabeans.OBDInfo;
import com.jsf2leaf.model.*;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by David
 */
@Named
@ViewScoped
public class DetailController implements Serializable {
    private List<OBDInfo> logs;
    private List<GPSlog> positions;
    private List<CameraEvent> cameraEvents;
    private Map map;

    private Integer selectedTripId;

    @Inject
    private TripRetriever tripRetriever;

    @PostConstruct
    public void initialize() {
        String selectedTripId = Faces.getExternalContext().getRequestParameterMap().get("selectedTripId");
        this.selectedTripId = Integer.parseInt(selectedTripId);
        this.map = null;
    }

    public void loadLogs() {
        this.logs = tripRetriever.getOBDLogs(selectedTripId);
    }
    public void loadMap() {
        this.positions = tripRetriever.getGPSLogs(selectedTripId);

        Layer markerLayer = new Layer();
        Layer polyLayer = new Layer();
        Polyline polyLine = new Polyline();

        if (!positions.isEmpty()){
            GPSlog firstPos = positions.get(0);
            LatLong position = new LatLong(firstPos.getLatitude().toString(), firstPos.getLongitude().toString());
            Marker marker = new Marker(position, "Start at " + firstPos.getLog().getTime());
            Icon iconStart = new Icon(25, 41, 25, 41, -12, -45, "img:marker-icon-start.png");
            marker.setIcon(iconStart);
            markerLayer.addMarker(marker);

            GPSlog lastPos = positions.get(positions.size()-1);
            LatLong position2 = new LatLong(lastPos.getLatitude().toString(), lastPos.getLongitude().toString());
            Marker marker2 = new Marker(position2, "Finish at " + lastPos.getLog().getTime());
            Icon iconFinish = new Icon(25, 41, 25, 41, -12, -45, "img:marker-icon-finish.png");
            marker2.setIcon(iconFinish);
            markerLayer.addMarker(marker2);


            for (GPSlog p : positions) {
                LatLong position3 = new LatLong(p.getLatitude().toString(), p.getLongitude().toString());
                polyLine.addPoint(position3);
            }

            Icon iconEvent = new Icon(25, 41, 25, 41, -12, -45, "img:marker-icon-event.png");
            for (GPSlog event : positions.stream().filter(p -> p.getLog().isEvent()).collect(Collectors.toList())) {
                LatLong positionE = new LatLong(event.getLatitude().toString(), event.getLongitude().toString());
                Marker markerE = new Marker(positionE, "Event at " + event.getLog().getTime());
                markerE.setIcon(iconEvent);
                markerLayer.addMarker(markerE);
            }

            polyLayer.addPolyline(polyLine);
            polyLayer.setPan(true);

        //TODO: associate a marker with a obd log, change colour, click a marker
        //TODO: responsive (javascript on resize)
        //TODO: refresh map!!!

            // Coordenadas Mallorca
            String lat = "39.616667";
            String lng = "2.983333";
            int zoom = 12;

            this.map = new Map().setWidth("578px").setHeight("360px").setZoom(zoom)
                    .setCenter(
                            new LatLong(
                                    lat,lng
                            )
                    )
                    .addLayer(markerLayer).addLayer(polyLayer);
            this.map.setMiniMap(false);
        }
    }
    public void loadCameraEvents() {
        this.cameraEvents = this.tripRetriever.getCameraEvents(selectedTripId);
    }


    //Getters / Setters

    public List<OBDInfo> getLogs() {
        return logs;
    }

    public void setLogs(List<OBDInfo> logs) {
        this.logs = logs;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<CameraEvent> getCameraEvents() {
        return cameraEvents;
    }

    public void setCameraEvents(List<CameraEvent> cameraEvents) {
        this.cameraEvents = cameraEvents;
    }

    public Integer getSelectedTripId() {
        return selectedTripId;
    }

    public void setSelectedTripId(Integer selectedTripId) {
        this.selectedTripId = selectedTripId;
    }
}
