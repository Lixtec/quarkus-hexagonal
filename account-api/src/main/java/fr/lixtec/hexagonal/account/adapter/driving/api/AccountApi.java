package fr.lixtec.hexagonal.account.adapter.driving.api;

import java.math.BigDecimal;
import java.util.List;

import fr.lixtec.hexagonal.account.application.port.driven.CreateAccountUseCase;
import fr.lixtec.hexagonal.account.application.port.driven.DepositMoneyUseCase;
import fr.lixtec.hexagonal.account.application.port.driven.TransferMoneyUseCase;
import fr.lixtec.hexagonal.account.application.port.driven.WithdrawMoneyUseCase;
import fr.lixtec.hexagonal.account.application.port.driving.LoadAccountPort;
import fr.lixtec.hexagonal.account.application.port.driving.ManageAccountPort;
import fr.lixtec.hexagonal.account.domain.model.Account;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

/**
 * Cette interface represente le contrat d'interface pour acceder 
 * au service Account 
 * 
 * @author Ludovic.Terral
 *
 */
@Path("/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AccountApi extends CreateAccountUseCase, DepositMoneyUseCase, TransferMoneyUseCase, WithdrawMoneyUseCase, LoadAccountPort, ManageAccountPort
{
    @Override @GET List<Account> listAccounts();
    @Override @GET @Path("{id}") Account getAccount(@PathParam("id") String accountId);

    @Override @PUT @Path("create/{clientId}")  Account createAccount(@PathParam("clientId") String clientId);
    @Override @POST Account saveAccount(Account account);
    @Override @PUT @Path("{id}/deposit") void deposit(@PathParam("id") String accountId, @QueryParam("value") BigDecimal value, @QueryParam("description") String description);
    @Override @PUT @Path("{id}/withdraw") void withdraw(@PathParam("id") String accountId, @QueryParam("value") BigDecimal value, @QueryParam("description") String description);
    @Override @PUT @Path("{origin}/transferTo/{destination}") void transferTo(@PathParam("origin") String originId, @PathParam("destination") String destinationId, @QueryParam("value") BigDecimal value, @QueryParam("description") String description);
}
