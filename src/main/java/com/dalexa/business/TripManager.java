package com.dalexa.business;

import com.dalexa.business.entities.Log;
import com.dalexa.business.entities.Trip;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by David
 */
public class TripManager {
    @PersistenceContext
    private EntityManager em;

    public Trip createTrip(){
        return em.merge(new Trip());
    }

    public Trip findTrip(int tripId) {
        return em.find(Trip.class, tripId);
    }

    public Log updateLog(Log log) {
        return em.merge(log);
    }
}
