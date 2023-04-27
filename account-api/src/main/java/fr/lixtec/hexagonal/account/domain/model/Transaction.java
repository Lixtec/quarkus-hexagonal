package fr.lixtec.hexagonal.account.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import fr.lixbox.io.json.JsonUtil;

/**
 * Cette entite represente un transaction bancaire
 * 
 * @author ludovic.Terral
 */
public class Transaction implements Serializable
{
    // ----------- Attribut -----------
    private static final long serialVersionUID = 202304262242L;

    private String transactionId;
    private BigDecimal value;
    private String description;
    private TransactionType transactionType;
    private Date transactionDate;

    

    // ----------- Methode -----------
    public Transaction()
    {
    }



    public Transaction(String transactionId, BigDecimal value, String description,
            TransactionType transactionType, Date transactionDate)
    {
        if (transactionId == null)
        {
            throw new IllegalArgumentException("TransactionId cannot be null");
        }
        if (value == null)
        {
            throw new IllegalArgumentException("Value cannot be null");
        }
        if (value.equals(BigDecimal.ZERO))
        {
            throw new IllegalArgumentException("Value cannot be zero");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        if (description == null)
        {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (transactionType == null)
        {
            throw new IllegalArgumentException("TransactionType cannot be null");
        }
        if (transactionDate == null)
        {
            throw new IllegalArgumentException("TransactionDate cannot be null");
        }
        this.transactionId = transactionId;
        this.value = value;
        this.description = description;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }



    public String getTransactionId()
    {
        return transactionId;
    }
    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }



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

    
    
    @Override
    public String toString()
    {
        return JsonUtil.transformObjectToJson(this, false);
    }
}