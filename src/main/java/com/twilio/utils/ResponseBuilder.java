package com.twilio.utils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.core.Response;

/**
 * Created by ivanbastidas on 2/6/15.
 */
public class ResponseBuilder {

    public static Response create(int httpCode, int responseCode, String message) {
        ObjectNode body = JsonNodeFactory.instance.objectNode();
        body.put("responseCode", responseCode);
        body.put("response", message);
        return Response.status(httpCode)
                .entity(body.toString())
                .header("Content-type", "application/json")
                .build();
    }
}
