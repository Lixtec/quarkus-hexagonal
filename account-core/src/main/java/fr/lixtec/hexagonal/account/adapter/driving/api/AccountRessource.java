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
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Cette interface represente le contrat d'interface pour acceder 
 * au service Account 
 * 
 * @author Ludovic.Terral
 *
 */
public class AccountRessource implements AccountApi
{
    // ----------- Attribut -----------
    private static final long serialVersionUID = 6172712996999868456L;

    @Inject @Named("CreateAccountUseCase") CreateAccountUseCase createAccountUseCase;
    @Inject @Named("DepositMoneyUseCase") DepositMoneyUseCase depositMoneyUseCase;
    @Inject @Named("TransferMoneyUseCase") TransferMoneyUseCase transferMoneyUseCase;
    @Inject @Named("WithdrawMoneyUseCase") WithdrawMoneyUseCase withdrawMoneyUseCase;
    @Inject @Named("LoadAccountPort") LoadAccountPort loadAccountPort;
    @Inject @Named("ManageAccountPort") ManageAccountPort manageAccountPort;
    
    

    // ----------- Methode -----------
    @Override
    public List<Account> listAccounts()
    {
        return this.loadAccountPort.listAccounts();
    }
    
    
    
    @Override
    public Account getAccount(String accountId) 
    {
        return this.loadAccountPort.getAccount(accountId);
    };
    
    

    @Override
    public Account createAccount(String clientId) 
    {
        return this.createAccountUseCase.createAccount(clientId);
    }
    
    

    @Override
    public Account saveAccount(Account account) 
    {
        return this.manageAccountPort.saveAccount(account);
    }
    
    

    @Override
    public void deposit(String accountId, BigDecimal value, String description) 
    {
        this.depositMoneyUseCase.deposit(accountId, value, description);
    }
    
    
    @Override 
    public void withdraw(String accountId, BigDecimal value, String description)
    {
        this.withdrawMoneyUseCase.withdraw(accountId, value, description);
    }
    
    
    
    @Override 
    public void transferTo(String originId, String destinationId, BigDecimal value, String description)
    {
        this.transferMoneyUseCase.transferTo(originId, destinationId, value, description);
    }
}
