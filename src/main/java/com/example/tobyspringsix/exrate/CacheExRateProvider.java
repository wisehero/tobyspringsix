package com.example.tobyspringsix.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.tobyspringsix.payment.ExRateProvider;

public class CacheExRateProvider implements ExRateProvider {
	private final ExRateProvider target;

	private BigDecimal cachedExRate;
	private LocalDateTime cacheExpiryDate;

	public CacheExRateProvider(ExRateProvider target) {
		this.target = target;
	}

	@Override
	public BigDecimal getExRate(String currency) throws IOException {
		if (cachedExRate == null || cacheExpiryDate.isBefore(LocalDateTime.now())) {
			cachedExRate = this.target.getExRate(currency);
			cacheExpiryDate = LocalDateTime.now().plusSeconds(3);

			System.out.println("Cache Update");
		}

		return cachedExRate;
	}
}
