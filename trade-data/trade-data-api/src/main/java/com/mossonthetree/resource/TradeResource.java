package com.mossonthetree.resource;

import com.mossonthetree.model.Trade;
import com.mossonthetree.service.TradeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;

@Path("api/trades")
public class TradeResource {
    private final TradeService service;

    public TradeResource(TradeService service) {
        this.service = service;
    }

    @POST()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Trade create(@RequestBody() Trade trade) {
        return service.create(trade);
    }

    @GET()
    @Path("{account-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Trade> get(@PathParam("account-id") String accountId) {
        return service.get(accountId);
    }

    @PUT()
    @Path("{trade-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Trade close(@PathParam("trade-id") String tradeId) {
        return service.close(tradeId);
    }

    @DELETE()
    @Path("{trade-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Trade delete(@PathParam("trade-id") String tradeId) {
        return service.delete(tradeId);
    }
}
