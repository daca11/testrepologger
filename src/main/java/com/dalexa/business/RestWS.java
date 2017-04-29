package com.dalexa.business;

import com.dalexa.business.entities.TestEntity;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by David
 */
@Stateless
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestWS {
    @GET
    public Response testMethod(){
        return Response.ok("Successful!").build();
    }

    @POST
    public Response postMethod(TestEntity entity){
        return Response.ok(entity).build();
    }
}
