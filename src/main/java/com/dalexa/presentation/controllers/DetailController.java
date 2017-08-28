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

        for (GPSlog p : positions) {
            LatLong position = new LatLong(p.getLatitude().toString(), p.getLongitude().toString());
            markerLayer.addMarker(new Marker(position));
            polyLine.addPoint(position);
        }

        polyLayer.addPolyline(polyLine);

        //TODO: associate a marker with a obd log, change colour, click a marker
        //TODO: responsive (javascript on resize)
        //TODO: refresh map!!!

        if (!positions.isEmpty()) {
            GPSlog first = positions.get(0);
            this.map = new Map().setWidth("578px").setHeight("360px").setZoom(13)
                    .setCenter(
                            new LatLong(
                                    first.getLatitude().toString(),
                                    first.getLongitude().toString()
                            )
                    )
                    .addLayer(markerLayer).addLayer(polyLayer);
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
