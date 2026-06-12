package com.acme.account;

import java.math.BigDecimal;

public class TransactionalAccount implements Account {

	@Override
	public void deposit(BigDecimal amount, TransactionType type) {
		// TODO Auto-generated method stub
	}

	@Override
	public void withdraw(BigDecimal amount, TransactionType type) {
		// TODO Auto-generated method stub
	}

	@Override
	public BigDecimal getBalance() {
		// TODO Auto-generated method stub
		return BigDecimal.ZERO;
	}

	@Override
	public String getHistory() {
		// TODO Auto-generated method stub
		return null;
	}

}
