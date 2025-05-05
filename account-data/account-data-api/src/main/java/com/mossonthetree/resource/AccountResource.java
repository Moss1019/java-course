package com.mossonthetree.resource;

import com.mossonthetree.client.AccountClient;
import com.mossonthetree.model.Account;
import com.mossonthetree.service.AccountService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;

@Path("api/accounts")
public class AccountResource implements AccountClient {
    private final AccountService service;

    public AccountResource(AccountService service) {
        this.service = service;
    }

    @POST()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Account create(@RequestBody() Account account) {
        return service.create(account);
    }

    @GET()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAll() {
        return service.getAll();
    }

    @GET()
    @Path("{account-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getById(@PathParam("account-id") String accountId) {
        return service.getById(accountId);
    }

    @DELETE()
    @Path("{account-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account delete(@PathParam("account-id") String accountId) {
        return service.delete(accountId);
    }

    @PUT()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Account update(@RequestBody() Account account) {
        return service.update(account);
    }
}
