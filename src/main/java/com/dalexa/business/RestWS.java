package com.dalexa.business;

import com.dalexa.business.entities.Log;
import com.dalexa.business.entities.Trip;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by David
 */
@Stateless
@Path("/")
public class RestWS {

    @Inject private TripManager tripManager;

    /**
     * Creates a new log entry in DB
     * @return the ID of the created log entry
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response createTrip(){
        Trip newTrip = tripManager.createTrip();
        return Response.ok(newTrip.getTripId()).build();
    }

    /**
     * Updates existing log entry with GPS and OBD data
     * @param log the received log from Autobox
     * @return A response code
     * Response 200 if persisted
     * Response 404 if trip not found
     * Response 403 if trip closed
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLog(Log log){
        Trip trip = tripManager.findTrip(log.getTrip().getTripId());
        if (trip == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
//        else if (trip.getEndDate() != null){
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
        log.setTrip(trip);
        if (log.getGpsLog() != null){
            log.getGpsLog().setLog(log);
        }
        if (log.getObdLog() != null){
            log.getObdLog().setLog(log);
        }
        tripManager.updateLog(log);
        //TODO: In the python client -> if not 200 then save it for later
        return Response.ok().build();
    }


}
