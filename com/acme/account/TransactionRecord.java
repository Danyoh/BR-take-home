package com.acme.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Immutable transaction record of an account
 * Contains action, type, amount, and timestamp
 * */


public record TransactionRecord(
    TransactionAction action,
    TransactionType type,
    BigDecimal amount,
    LocalDateTime timestamp) {

        // Time formatted year month date with 24 hour time, minutes and seconds
        private static final DateTimeFormatter dateTimeFormatted = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        public TransactionRecord(TransactionAction action, TransactionType type, BigDecimal amount) {
            this(action, type, amount, LocalDateTime.now());
        }

    @Override
    public String toString() {
        String quantity = switch(type) {
            // To plain string for big decimal
            case CASH -> "$" + amount.toPlainString();
            case STOCK -> amount.toBigInteger() + " unit(s) of " + Stock.ACME.ticker();
            default -> amount.toPlainString() + " (" + type + ")";
        };
        return "[" + timestamp.format(dateTimeFormatted) + "]" + action + "  " + type + " " + quantity;
    }
}

