package fr.lixtec.hexagonal.account.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import fr.lixbox.io.json.JsonUtil;

/**
 * Cette entite represente un compte bancaire
 * 
 * @author ludovic.Terral
 */
public class Account implements Serializable
{
    // ----------- Attribut -----------
    private static final long serialVersionUID = 20230426223402L;

    private String accountId;   
    private BigDecimal balance;
    private String clientId;
    
    private List<Transaction> transactions;


    
    // ----------- Methode -----------
    public Account(String clientId, String accountId, List<Transaction> transactions)
    {
        if (clientId == null)
        {
            throw new IllegalArgumentException("clientId cannot be null");
        }
        if (accountId == null)
        {
            throw new IllegalArgumentException("accountId cannot be null");
        }
        if (transactions == null)
        {
            throw new IllegalArgumentException("transactions cannot be null");
        }
        this.clientId = clientId;
        this.accountId = accountId;
        this.transactions = new ArrayList<Transaction>(transactions);
        BigDecimal currentBalance = new BigDecimal(0);
        for (Transaction transaction : transactions)
        {
            if (transaction.getTransactionType() == TransactionType.Credit)
            {
                currentBalance = currentBalance.add(transaction.getValue());
            }
            else
            {
                currentBalance = currentBalance.subtract(transaction.getValue());
                if (currentBalance.compareTo(BigDecimal.ZERO) < 0)
                {
                    throw new IllegalArgumentException("Balance should be greater than zero");
                }
            }
        }
        this.balance = currentBalance;
    }
    
    
    
    public String getAccountId()
    {
        return accountId;
    }
    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }


    public BigDecimal getBalance()
    {
        return balance;
    }
    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }


    public String getClientId()
    {
        return clientId;
    }
    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }


    
    public List<Transaction> getTransactions()
    {
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    
    
    public void deposit(BigDecimal value, String description) {
        Transaction trans = new Transaction(UUID.randomUUID().toString(), value, description, TransactionType.Credit, new Date());
        this.transactions.add(trans);
        this.balance = this.balance.add(value);
    }

    public void withdraw(BigDecimal value, String description) {
        if (value.compareTo(this.balance) >0) {
            throw new IllegalArgumentException("Withdraw is greater than account balance");
        }
        Transaction trans = new Transaction(UUID.randomUUID().toString(), value, description, TransactionType.Debit, new Date());
        this.transactions.add(trans);
        this.balance = this.balance.subtract(value);
    }
    
    
    @Override
    public String toString()
    {
        return JsonUtil.transformObjectToJson(this, false);
    }
}
