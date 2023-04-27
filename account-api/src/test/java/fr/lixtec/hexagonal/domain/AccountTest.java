package fr.lixtec.hexagonal.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.lixtec.hexagonal.account.domain.model.Account;
import fr.lixtec.hexagonal.account.domain.model.Transaction;
import fr.lixtec.hexagonal.account.domain.model.TransactionType;

public class AccountTest
{
    @Test
    public void testConstructorEmptyClientId()
    {
        try
        {
            new Account(null, null, null);
            Assertions.fail();
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("clientId cannot be null", e.getMessage());
        }
    }



    @Test
    public void testConstructorEmptyAccountId()
    {
        try
        {
            new Account("1", null, null);
            Assertions.fail();
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("accountId cannot be null", e.getMessage());
        }
    }



    @Test
    public void testConstructorEmptyTransactionList()
    {
        try
        {
            new Account("1", "2", null);
            Assertions.fail();
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("transactions cannot be null", e.getMessage());
        }
    }



    @Test
    public void testConstructorNoTransactions()
    {
        Account account = new Account("1", "2", Collections.emptyList());
        Assertions.assertEquals(new BigDecimal(0), account.getBalance());
        Assertions.assertEquals("1", account.getClientId());
        Assertions.assertEquals("2", account.getAccountId());
        Assertions.assertEquals(0, account.getTransactions().size());
    }



    @Test
    public void testConstructorCreditOk()
    {
        Account account = new Account("1", "2", Arrays.asList(new Transaction("1",
                new BigDecimal(10), "desc", TransactionType.Credit, new Date())));
        Assertions.assertEquals(new BigDecimal(10), account.getBalance());
        Assertions.assertEquals(1, account.getTransactions().size());
    }



    @Test
    public void testConstructorCreditSum()
    {
        Account account = new Account("1", "2",
                Arrays.asList(
                        new Transaction("1", new BigDecimal(10), "desc", TransactionType.Credit,
                                new Date()),
                        new Transaction("2", new BigDecimal(30), "desc", TransactionType.Credit,
                                new Date())));
        Assertions.assertEquals(new BigDecimal(40), account.getBalance());
        Assertions.assertEquals(2, account.getTransactions().size());
    }



    @Test
    public void testConstructorCreditDebitOk()
    {
        Account account = new Account("1", "2",
                Arrays.asList(
                        new Transaction("1", new BigDecimal(10), "desc", TransactionType.Credit,
                                new Date()),
                        new Transaction("2", new BigDecimal(5), "desc", TransactionType.Debit,
                                new Date())));
        Assertions.assertEquals(new BigDecimal(5), account.getBalance());
        Assertions.assertEquals(2, account.getTransactions().size());
    }



    @Test
    public void testConstructorCreditDebitError()
    {
        try
        {
            new Account("1", "2",
                    Arrays.asList(
                            new Transaction("1", new BigDecimal(10), "desc", TransactionType.Credit,
                                    new Date()),
                            new Transaction("2", new BigDecimal(50), "desc", TransactionType.Debit,
                                    new Date())));
            Assertions.fail();
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("Balance should be greater than zero", e.getMessage());
        }
    }



    @Test
    public void testDepositOk()
    {
        Account account = new Account("1", "2", Arrays.asList(new Transaction("1",
                new BigDecimal(10), "desc", TransactionType.Credit, new Date())));
        account.deposit(new BigDecimal(5), "description");
        Assertions.assertEquals(new BigDecimal(15), account.getBalance());
        Assertions.assertEquals(2, account.getTransactions().size());
    }



    @Test
    public void testWithdrawOk()
    {
        Account account = new Account("1", "2", Arrays.asList(new Transaction("1",
                new BigDecimal(10), "desc", TransactionType.Credit, new Date())));
        account.withdraw(new BigDecimal(5), "description");
        Assertions.assertEquals(new BigDecimal(5), account.getBalance());
        Assertions.assertEquals(2, account.getTransactions().size());
    }



    @Test
    public void testWithdrawError()
    {
        try
        {
            Account account = new Account("1", "2", Arrays.asList(new Transaction("1",
                    new BigDecimal(10), "desc", TransactionType.Credit, new Date())));
            account.withdraw(new BigDecimal(11), "description");
            Assertions.fail();
        }
        catch (IllegalArgumentException e)
        {
            Assertions.assertEquals("Withdraw is greater than account balance", e.getMessage());
        }
    }
}