package fr.lixtec.hexagonal.account.adapter.driven.persistence.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import fr.lixtec.hexagonal.account.domain.model.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Ce dto represente un transaction bancaire
 * 
 * @author ludovic.Terral
 */
@Entity
public class TransactionDto implements Serializable
{
    // ----------- Attribut -----------
    private static final long serialVersionUID = 202304262242L;

    private String transactionId;
    private BigDecimal value;
    private String description;
    private TransactionType transactionType;
    private Date transactionDate;
    private AccountDto account;

    

    // ----------- Methode -----------
    public TransactionDto()
    {
    }



    public TransactionDto(String transactionId, BigDecimal value, String description, TransactionType transactionType, Date transactionDate, AccountDto account) {
        this.transactionId = transactionId;
        this.value = value;
        this.description = description;
        this.transactionType = transactionType;
        this.account = account;
        this.transactionDate = transactionDate;
    }



    @Id 
    @GeneratedValue(generator="transaction-uuid")
    @GenericGenerator(name="transaction-uuid", strategy = "uuid")
    public String getTransactionId()
    {
        return transactionId;
    }
    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }



    @Column(name = "valeur")
    public BigDecimal getValue()
    {
        return value;
    }
    public void setValue(BigDecimal value)
    {
        this.value = value;
    }



    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }


    
    @Enumerated(EnumType.STRING)
    public TransactionType getTransactionType()
    {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType)
    {
        this.transactionType = transactionType;
    }



    public Date getTransactionDate()
    {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate)
    {
        this.transactionDate = transactionDate;
    }



    @ManyToOne(fetch = FetchType.EAGER)
    public AccountDto getAccount()
    {
        return account;
    }
    public void setAccount(AccountDto account)
    {
        this.account = account;
    }
    
    
    
    @Override
    public String toString()
    {
        return "";
//        return JsonUtil.transformObjectToJson(this, false);
    }
}