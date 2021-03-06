package com.admios.services;

import com.admios.exceptions.PhoneNumberFormatException;
import com.admios.manager.PhoneCallManager;
import com.admios.utils.ResponseBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by ivanbastidas on 2/6/15.
 */
@Path("/phonecall")
public class PhoneCallServices {

    /*
    * Record the fact that a phone call happened between 2 numbers
    */
    @POST
    @Path("/phoneCallHappened")
    @Produces("application/json")
    public Response phoneCallHappened(String callDataJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode callData;
        try {
            callData = objectMapper.readTree(callDataJson);
        } catch (IOException e) {
            return ResponseBuilder.create(400, -1, "The Json with the call data is not valid");
        }
        try {
            PhoneCallManager phoneCallManager = PhoneCallManager.getInstance();
            phoneCallManager.phoneCallHappened(callData.get("from").asText(), callData.get("to").asText());
        } catch (PhoneNumberFormatException e) {
            return ResponseBuilder.create(400, -2, e.getMessage());
        }
        return ResponseBuilder.create(200, 0, "Call registered successfully");
    }

    /*
    * Return whether a phone call has happened between the 2 numbers
    * Params:
    * from: number that made the call
    * to: number that was called
    */
    @GET
    @Path("/didPhoneCallHappen")
    @Produces("application/json")
    public Response didPhoneCallHappen(@QueryParam("from") String from, @QueryParam("to") String to) {
        PhoneCallManager phoneCallManager = PhoneCallManager.getInstance();
        boolean callHappen;
        try {
            callHappen = phoneCallManager.didPhoneCallHappen(from, to);
        } catch (PhoneNumberFormatException e) {
            return ResponseBuilder.create(400, -2, e.getMessage());
        }
        return ResponseBuilder.create(200, 0, String.valueOf(callHappen));
    }

    /*
    * Return whether a phone call has happened between the 2 numbers in any order between the numbers
    */
    @GET
    @Path("/didPhoneCallHappenAnyOrder")
    @Produces("application/json")
    public Response didPhoneCallHappenAnyOrder(@QueryParam("numberA") String numberA, @QueryParam("numberB") String numberB) {
        PhoneCallManager phoneCallManager = PhoneCallManager.getInstance();
        boolean callHappen;
        try {
            callHappen = phoneCallManager.didPhoneCallHappenAnyOrder(numberA, numberB);
        } catch (PhoneNumberFormatException e) {
            return ResponseBuilder.create(400, -2, e.getMessage());
        }
        return ResponseBuilder.create(200, 0, String.valueOf(callHappen));
    }
}
