package fr.lixtec.hexagonal.account.application.port.driving;

import java.io.Serializable;

import fr.lixtec.hexagonal.account.domain.model.Account;

/**
 * Cette classe represente le port pour les adapters liés à ManageAccount
 * 
 * @author Ludovic.Terral
 */
public interface ManageAccountPort extends Serializable
{
    Account saveAccount(Account account);
}
