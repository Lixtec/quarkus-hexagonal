package fr.lixtec.hexagonal.account.application.port.driving;

import java.io.Serializable;
import java.util.List;

import fr.lixtec.hexagonal.account.domain.model.Account;

/**
 * Cette classe represente le port pour les adapters liés à LoadAccount
 * 
 * @author Ludovic.Terral
 */
public interface LoadAccountPort extends Serializable
{    
    List<Account> listAccounts();
    Account getAccount(String accountId);
}
