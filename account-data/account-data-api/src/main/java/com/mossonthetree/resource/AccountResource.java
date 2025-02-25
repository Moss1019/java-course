package com.mossonthetree.resource;

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
public class AccountResource {
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

    @DELETE()
    @Path("{user-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account delete(@PathParam("user-name") String userName) {
        return service.delete(userName);
    }

    @PUT()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Account update(@RequestBody() Account account) {
        return service.update(account);
    }
}
