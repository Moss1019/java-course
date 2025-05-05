package com.mossonthetree.client;

import com.mossonthetree.model.Account;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("api/accounts")
@RegisterRestClient(configKey = "account-client")
public interface AccountClient {
    @POST()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    Account create(@RequestBody() Account account);

    @GET()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    List<Account> getAll();

    @GET()
    @Path("{account-id}")
    @Produces(MediaType.APPLICATION_JSON)
    Account getById(@PathParam("account-id") String accountId);

    @DELETE()
    @Path("{account-id}")
    @Produces(MediaType.APPLICATION_JSON)
    Account delete(@PathParam("account-id") String accountId);

    @PUT()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    Account update(@RequestBody() Account account);
}
