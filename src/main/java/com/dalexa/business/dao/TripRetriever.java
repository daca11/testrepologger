package com.dalexa.business.dao;

import com.dalexa.business.entities.Log;
import com.dalexa.business.entities.Trip;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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
}
