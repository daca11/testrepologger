package com.dalexa.business.dao;

import com.dalexa.business.entities.*;
import com.dalexa.business.javabeans.OBDInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by David
 */
@Stateless
public class TripRetriever {
    @PersistenceContext
    private Session session; //TODO: SESSION = EM.GETDELEGATE

    public List<Integer> getTripIds() {
        return session.createCriteria(Trip.class)
                .setProjection(Projections.id())
                //TODO: projections time (with alias) and addorder desc time alias
                .list();
    }

    public Object[] getTripDates(int tripId){
        Criteria c = session.createCriteria(Log.class)
                .add(Restrictions.eq("trip.tripId", tripId))
                .setProjection(
                        Projections.projectionList()
                        .add(Projections.min("time"))
                        .add(Projections.max("time"))
                );

        return (Object[]) c.uniqueResult();
    }

    public List<Log> getLogs(int tripId) {
        return session.createCriteria(Log.class)
                .add(Restrictions.eq("trip.tripId", tripId))
                .addOrder(Order.asc("time"))
                .list();
    }

    public List<OBDInfo> getOBDLogs(int tripId){
        return session.createCriteria(OBDlog.class)
                .createAlias("log", "l")
                .createAlias("l.trip", "t")
                .add(Restrictions.eq("t.tripId", tripId))
                .setProjection(
                        Projections.projectionList()
                        .add(Projections.property("obdlogId"), "id")
                        .add(Projections.property("l.time"), "time")
                        .add(Projections.property("rpm"), "rpm")
                        .add(Projections.property("speed"), "speed")
                        .add(Projections.property("throttle"), "throttle")
                        .add(Projections.property("load"), "load")
                        .add(Projections.property("fuel"), "fuel")
                        .add(Projections.property("l.event"), "event")
                )
                .setResultTransformer(Transformers.aliasToBean(OBDInfo.class))
                .list();
    }

    public List<GPSlog> getGPSLogs(int tripId){
        return session.createCriteria(GPSlog.class)
                .createAlias("log", "l")
                .createAlias("l.trip", "t")
                .add(Restrictions.eq("t.tripId", tripId))
                .list();
    }

    public List<CameraEvent> getCameraEvents(int tripId){
        return session.createCriteria(CameraEvent.class)
                .createAlias("trip", "t")
                .add(Restrictions.eq("t.tripId", tripId))
                .addOrder(Order.asc("time"))
                .addOrder(Order.asc("cameraId"))
                .list();
    }

    public Boolean hasEvents(Integer tripId) {
        Long eventCount = (Long) session.createCriteria(CameraEvent.class)
                .createAlias("trip", "t")
                .add(Restrictions.eq("t.tripId", tripId))
                .setProjection(Projections.rowCount()).uniqueResult();
        return eventCount > 0;
    }
}
