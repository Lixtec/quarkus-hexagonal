package fr.lixtec.hexagonal.account.application.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import fr.lixtec.hexagonal.account.adapter.driven.persistence.AccountRepository;
import fr.lixtec.hexagonal.account.application.port.driven.CreateAccountUseCase;
import fr.lixtec.hexagonal.account.application.port.driven.DepositMoneyUseCase;
import fr.lixtec.hexagonal.account.application.port.driven.TransferMoneyUseCase;
import fr.lixtec.hexagonal.account.application.port.driven.WithdrawMoneyUseCase;
import fr.lixtec.hexagonal.account.application.port.driving.LoadAccountPort;
import fr.lixtec.hexagonal.account.application.port.driving.ManageAccountPort;
import fr.lixtec.hexagonal.account.domain.model.Account;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Ce service implemente les contrats du service Account autour des cas d'utilisation et des ports
 * 
 * @author Ludovic.Teral
 */
@ApplicationScoped
public class AccountServiceImpl implements 
    CreateAccountUseCase,
    TransferMoneyUseCase, WithdrawMoneyUseCase, DepositMoneyUseCase,
    LoadAccountPort, ManageAccountPort
{
    // ----------- Attribut -----------
    private static final long serialVersionUID = -4316973849311105501L;
    @Inject AccountRepository accountRepository;

    
    
    // ----------- Methode -----------
    @Override
    public void deposit(String accountId, BigDecimal value, String description)
    {
        Account account = this.accountRepository.getAccount(accountId);
        account.deposit(value, description);
        this.accountRepository.saveAccount(account);
    }



    @Override
    public void withdraw(String accountId, BigDecimal value, String description)
    {
        Account account = this.accountRepository.getAccount(accountId);
        account.withdraw(value, description);
        this.accountRepository.saveAccount(account);
    }



    @Override
    public void transferTo(String originAccountId, String destinationAccountId, BigDecimal value,
            String description)
    {
        Account originAccount = this.accountRepository.getAccount(originAccountId);
        Account destinationAccount = this.accountRepository.getAccount(destinationAccountId);
        originAccount.withdraw(value, description);
        destinationAccount.deposit(value, description);
        this.accountRepository.saveAccount(originAccount);
        this.accountRepository.saveAccount(destinationAccount);
    }



    @Override
    public List<Account> listAccounts()
    {
        return this.accountRepository.listAccounts();
    }



    @Override
    public Account getAccount(String accountId)
    {
        return this.accountRepository.getAccount(accountId);
    }



    @Override
    public Account createAccount(String clientId)
    {
        Account newAccount = new Account(clientId, UUID.randomUUID().toString(), Collections.emptyList());
        this.accountRepository.saveAccount(newAccount);
        return newAccount;
    }



    @Override
    public Account saveAccount(Account account)
    {
        return this.accountRepository.saveAccount(account);
    }
}
