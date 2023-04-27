package fr.lixtec.hexagonal.account.application.port.driven;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Cette classe represente le cas d'utilisation TransferMoney
 * 
 * @author Ludovic.Terral
 */
public interface TransferMoneyUseCase extends Serializable
{
    void transferTo(String originAccountId, String destinationAccountId, BigDecimal value,
            String description);
}
