package com.twilio.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.exceptions.PhoneNumberFormatException;
import com.twilio.manager.PhoneCallManager;
import com.twilio.utils.ResponseBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by ivanbastidas on 2/6/15.
 */
@Path("/phonecall")
public class PhoneCallServices {

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
}
