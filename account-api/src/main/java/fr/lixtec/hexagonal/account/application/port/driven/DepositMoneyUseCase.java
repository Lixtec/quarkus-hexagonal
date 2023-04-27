package fr.lixtec.hexagonal.account.application.port.driven;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Cette classe represente le cas d'utilisation DepositMoney
 * 
 * @author Ludovic.Terral
 */
public interface DepositMoneyUseCase extends Serializable
{
    void deposit(String accountId, BigDecimal value, String description);
}
