package com.dalexa.business.dao;

import com.dalexa.business.entities.Log;
import com.dalexa.business.entities.Trip;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by David
 */
@Stateless
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
