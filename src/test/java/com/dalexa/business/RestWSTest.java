package com.dalexa.business;

import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by David
 */
public class RestWSTest {
    @Test
    public void testMethod() throws Exception {
    }

    @Test
    public void postMethod() throws Exception {


        Response r = ClientBuilder.newClient()
                .target("http://localhost:8080/autologger/rest/video")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json("{\"message\":\"Hello!\"}"));

        System.out.println(r.getStatus());
        System.out.println(r.readEntity(String.class));
    }

}