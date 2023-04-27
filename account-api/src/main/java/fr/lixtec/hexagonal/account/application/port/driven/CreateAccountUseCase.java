package fr.lixtec.hexagonal.account.application.port.driven;

import java.io.Serializable;

import fr.lixtec.hexagonal.account.domain.model.Account;

/**
 * Cette classe represente le cas d'utilisation CreateAccount
 * 
 * @author Ludovic.Terral
 */
public interface CreateAccountUseCase extends Serializable
{
    Account createAccount(String clientId);
}
