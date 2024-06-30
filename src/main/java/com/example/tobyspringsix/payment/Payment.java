package com.example.tobyspringsix.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class Payment {
	private Long orderId;
	private String currency;
	private BigDecimal foreignCurrencyAmount;
	private BigDecimal exRate;
	private BigDecimal convertedAmount;
	private LocalDateTime validUntil;

	public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate,
		BigDecimal convertedAmount, LocalDateTime validUntil) {
		this.orderId = orderId;
		this.currency = currency;
		this.foreignCurrencyAmount = foreignCurrencyAmount;
		this.exRate = exRate;
		this.convertedAmount = convertedAmount;
		this.validUntil = validUntil;
	}

	public static Payment createPrepared(Long orderId, String currency, BigDecimal foreignCurrencyAmount,
		BigDecimal exRate,
		LocalDateTime now) {

		BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		LocalDateTime vaildUntil = now.plusMinutes(30);

		return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, vaildUntil);
	}

	public boolean isValid(Clock clock) {
		return LocalDateTime.now(clock).isBefore(this.validUntil);
	}

	public LocalDateTime getValidUntil() {
		return validUntil;
	}

	public BigDecimal getConvertedAmount() {
		return convertedAmount;
	}

	public BigDecimal getExRate() {
		return exRate;
	}

	public BigDecimal getForeignCurrencyAmount() {
		return foreignCurrencyAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public Long getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "Payment{" +
			"orderId=" + orderId +
			", currency='" + currency + '\'' +
			", foreignCurrencyAmount=" + foreignCurrencyAmount +
			", exRate=" + exRate +
			", convertedAmount=" + convertedAmount +
			", validUntil=" + validUntil +
			'}';
	}
}
