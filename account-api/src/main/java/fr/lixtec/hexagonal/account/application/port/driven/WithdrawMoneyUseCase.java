package fr.lixtec.hexagonal.account.application.port.driven;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Cette classe represente le cas d'utilisation WithdrawMoney
 * 
 * @author Ludovic.Terral
 */
public interface WithdrawMoneyUseCase extends Serializable
{
    void withdraw(String accountId, BigDecimal value, String description);
}
