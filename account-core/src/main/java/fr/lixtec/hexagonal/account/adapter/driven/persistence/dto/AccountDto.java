package fr.lixtec.hexagonal.account.adapter.driven.persistence.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

/**
 * Ce dto represente un compte bancaire
 * 
 * @author ludovic.Terral
 */
@Entity
public class AccountDto implements Serializable
{
    // ----------- Attribut -----------
    private static final long serialVersionUID = 20230426223402L;

    private String accountId;   
    private BigDecimal balance;
    private String clientId;
    
    private List<TransactionDto> transactions;


    
    // ----------- Methode -----------
    @Id 
    @GeneratedValue(generator="account-uuid")
    @GenericGenerator(name="account-uuid", strategy = "uuid")
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


    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    @OrderBy("transactionDate")
    public List<TransactionDto> getTransactions()
    {
        return transactions;
    }
    public void setTransactions(List<TransactionDto> transactions)
    {
        this.transactions = transactions;
    }

    

    
    
    @Override
    public String toString()
    {
        return "";
//        return JsonUtil.transformObjectToJson(this, false);
    }
}
