package com.acme.account;

import java.math.BigDecimal;

// Updated Double to BigDecimal

public interface Account {

	//Deposit money into the account
    public void deposit(BigDecimal amount, TransactionType type);

    //Withdraw money from the account
    public void withdraw(BigDecimal amount, TransactionType type);

    //Get current total balance
    public BigDecimal getBalance();

    //Get current cash balance
    public BigDecimal getCashBalance();

     //Get current stock balance
    public BigDecimal getStockBalance();
    
    //List transaction history
    public String getHistory();

}
