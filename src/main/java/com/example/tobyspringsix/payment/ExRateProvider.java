package com.example.tobyspringsix.payment;

import java.math.BigDecimal;

public interface ExRateProvider {
	BigDecimal getExRate(String currency);
}
