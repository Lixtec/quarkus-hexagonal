package fr.lixtec.hexagonal.account.adapter.driven.persistence;

import java.util.List;
import java.util.stream.Collectors;

import fr.lixtec.hexagonal.account.adapter.driven.persistence.dto.AccountDto;
import fr.lixtec.hexagonal.account.adapter.driven.persistence.dto.TransactionDto;
import fr.lixtec.hexagonal.account.application.port.driving.LoadAccountPort;
import fr.lixtec.hexagonal.account.application.port.driving.ManageAccountPort;
import fr.lixtec.hexagonal.account.domain.model.Account;
import fr.lixtec.hexagonal.account.domain.model.Transaction;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

/**
 * Cet adapter implémente tous les accès à la persistence pour le modèle de domaine Account
 * 
 * @author Ludovic.Terral
 */
@RequestScoped
public class AccountRepository implements LoadAccountPort, ManageAccountPort
{
    // ----------- Attribut -----------
    private static final long serialVersionUID = -8290247637105241074L;
    
    @Inject transient EntityManager repository; 
    
    
    
    // ----------- MMethode -----------
    public static Account toAccount(AccountDto model) {
        return new Account(model.getClientId(), 
                            model.getAccountId(), 
                            model.getTransactions()
                                    .stream()
                                    .map(AccountRepository::toTransaction)
                                    .collect(Collectors.toList()));
    }

    
    
    public static Transaction toTransaction(TransactionDto model) {
        return new Transaction(model.getTransactionId(), model.getValue(), model.getDescription(), 
                model.getTransactionType(), model.getTransactionDate());
    }
    

    
    private static List<TransactionDto> mapTransactionsToDto(List<Transaction> transactions, final AccountDto account) {
        return transactions.stream().map(transaction -> 
            new TransactionDto(transaction.getTransactionId(), 
                             transaction.getValue(),
                             transaction.getDescription(),
                             transaction.getTransactionType(),
                             transaction.getTransactionDate(),
                             account)).collect(Collectors.toList());
    }
    
    

    @Override
    @Transactional
    public List<Account> listAccounts()
    {
        return repository
                .createQuery("from AccountDto", AccountDto.class)
                .getResultList()
                .stream().map(AccountRepository::toAccount)
                    .collect(Collectors.toList());
    }
    


    @Override
    @Transactional
    public Account getAccount(String accountId)
    {
        return toAccount(repository.find(AccountDto.class, accountId));
    }
    
    

    @Override
    @Transactional
    public Account saveAccount(Account account)
    {
        AccountDto model = repository.find(AccountDto.class, account.getAccountId());
        if (model == null) {
            model = new AccountDto();
            model.setAccountId(account.getAccountId());
        }
        model.setBalance(account.getBalance());
        model.setClientId(account.getClientId());
        List<TransactionDto> transactions = mapTransactionsToDto(account.getTransactions(), model);
        model.setTransactions(transactions);
        model = repository.merge(model);
        model.getTransactions().forEach(transaction -> repository.merge(transaction));
        return toAccount(model);
    }
}
