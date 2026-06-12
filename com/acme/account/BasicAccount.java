package com.acme.account;

import java.math.BigDecimal;

//Changed over to BigDecimal e.g. signum, ZERO, add, subtract, toPlainString

public class BasicAccount implements Account{
    private BigDecimal balance;

    // Constructor
    public BasicAccount(BigDecimal initialBalance) {
        if (initialBalance != null && initialBalance.signum() >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Initial balance cannot be negative.");
            this.balance = BigDecimal.ZERO;
        }
    }

    // Deposit method
    @Override
    public void deposit(BigDecimal amount, TransactionType type) {
    	if (type != TransactionType.CASH) {
    		System.out.println("Only cash deposits are supported.");
    	}
    	else if (amount == null || amount.signum() <= 0) {
    		System.out.println("Deposit amount must be positive.");
    	}
    	else {
            balance = balance.add(amount);
            System.out.println("Deposited: $" + amount);
        }
    }

    // Withdrawal method
    @Override
    public void withdraw(BigDecimal amount, TransactionType type) {
    	if (type != TransactionType.CASH) {
    		System.out.println("Only cash withdrawals are supported.");
    	}
    	else if (amount == null || amount.signum() <= 0) {
    		System.out.println("Withdrawal amount must be positive.");
    	}
    	else if (amount.compareTo(balance) > 0) {
            System.out.println("Insufficient balance.");
    	}
    	else {
            balance = balance.subtract(amount);
            System.out.println("Withdrawn: $" + amount);
        }
    }

    // Get current balance
    @Override
    public BigDecimal getBalance() {
        return this.balance; 
    }

	@Override
	// Print Transaction History
	public String getHistory() {
		return "Transactional history not available.";
	}
}
