package com.mossonthetree.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.Map;

@Path("api/health")
public class HealthResource {
    @GET()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getHealth() {
        Map<String, String> res = new HashMap<>();
        res.put("status", "UP");
        return res;
    }
}
