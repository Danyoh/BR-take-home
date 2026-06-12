package com.acme.account;

import java.math.BigDecimal;

// Simulating API data from a stock

public record Stock (String ticker, BigDecimal price) {

    // Hard coded default stock: ACME @ $5.00 per share

    public static final Stock ACME = new Stock("ACME", new BigDecimal("5.00"));

    public Stock {
        if (ticker == null || ticker.isBlank()) {
            throw new IllegalArgumentException("Error: Ticker cannot be blank.");
        }

        // Signum since we are using Big Decimal
        if (price == null || price.signum() <= 0) {
            throw new IllegalArgumentException("Error: Price has to be positive.");
        }
    }
    
}
