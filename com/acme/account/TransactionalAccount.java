package com.acme.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** TransactionAccount supports both cash and stock transactions with a transaction history (TransactionRecord)
 * Initial balance will have the transaction as OPENING
 * Formula: Cash Balance = Total Cash Deposits – Total Cash Withdraws
 * 			Stock Balance = (Total Stock Deposits – Total Stock Withdraws) * Price
 * 			Total Balance = Cash Balance + Stock Balance
 */

public class TransactionalAccount implements Account {

	private BigDecimal cashBalance;
	private BigDecimal stockUnits;
	private final List<TransactionRecord> history;

	public TransactionalAccount(BigDecimal initialCash) {
		if (initialCash == null || initialCash.signum() < 0) {
			System.out.println("Error: Initial balance cannot be negative");
			this.cashBalance = BigDecimal.ZERO;
		} else {
			this.cashBalance = initialCash;
		}
		this.stockUnits = BigDecimal.ZERO;
		this.history = new ArrayList<>();

		// Initial balance log
		if (this.cashBalance.signum() > 0) {
			history.add(new TransactionRecord(TransactionAction.OPENING, TransactionType.CASH, this.cashBalance));
		}
	}

	@Override
	public void deposit(BigDecimal amount, TransactionType type) {
		if (type == null) {
			System.out.println("Error: Transaction type must be specified.");
			return;
		}

		if (amount == null || amount.signum() <= 0) {
			System.out.println("Error: Deposit amount must be positive.");
			return;
		}

		switch(type) {
			case CASH -> {
				cashBalance = cashBalance.add(amount);
				history.add(new TransactionRecord(TransactionAction.DEPOSIT, TransactionType.CASH, amount));
				System.out.println("Deposited: $" + amount);
			}
			case STOCK -> {
				if (amount.stripTrailingZeros().scale() > 0){
					System.out.println("Error: Stock units must be a discrete integer.");
					return;
				}
				stockUnits = stockUnits.add(amount);
				history.add(new TransactionRecord(TransactionAction.DEPOSIT, TransactionType.STOCK, amount));
				System.out.println("Deposited: " + amount.toBigInteger() + " unit(s) of " + Stock.ACME.ticker());
			}
			default -> System.out.println("Error: Unsupported transaction type: " + type);
		}
	}

	@Override
	public void withdraw(BigDecimal amount, TransactionType type) {
		if (type == null) {
			System.out.println("Error: Transaction type must be specified.");
			return;
		}

		if (amount == null || amount.signum() <= 0) {
			System.out.println("Error: Withdrawal amount must be positive.");
			return;
		}

		switch(type) {
			case CASH -> {
				if (amount.compareTo(cashBalance) > 0) {
					System.out.println("Error: Insufficient cash balance.");
					return;
				}
				cashBalance = cashBalance.add(amount);
				history.add(new TransactionRecord(TransactionAction.DEPOSIT, TransactionType.CASH, amount));
				System.out.println("Withdrawn: $" + amount);
			}
			case STOCK -> {
				if (amount.stripTrailingZeros().scale() <= 0){
					System.out.println("Error: Stock units must be a discrete integer.");
					return;
				}
				stockUnits = stockUnits.add(amount);
				history.add(new TransactionRecord(TransactionAction.DEPOSIT, TransactionType.STOCK, amount));
				System.out.println("Withdrawn: $" + amount + " unit(s) of " + Stock.ACME.ticker());
			}
			default -> System.out.println("Error: Unsupported transaction type: " + type);
		}
	}

	@Override
	public BigDecimal getBalance() {
		// TODO Auto-generated method stub
		return 		stockUnits.multiply(Stock.ACME.price());
	}

	@Override
	public String getHistory() {
		// TODO Auto-generated method stub
		if (history.isEmpty()) {
			return "No transactions recorded";
		}

		StringBuilder sb = new StringBuilder();
		for (TransactionRecord t: history) {
			sb.append(t).append(System.lineSeparator());

		}
		return sb.toString().stripTrailing();
	}

}
