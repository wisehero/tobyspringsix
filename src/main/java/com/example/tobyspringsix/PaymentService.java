package com.example.tobyspringsix;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

	private final ExRateProvider exRateProvider;

	public PaymentService(WebApiExRatePaymentProvider exRateProvider) {
		this.exRateProvider = new We;
	}

	public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws
		IOException {
		BigDecimal exRate = exRateProvider.getExRate(currency);
		BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

		return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount,
			validUntil);
	}

}
