package com.example.tobyspringsix;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.tobyspringsix.payment.ExRateProvider;
import com.example.tobyspringsix.payment.ExRateProviderStub;
import com.example.tobyspringsix.payment.PaymentService;

@Configuration
public class TestPaymentConfig {

	// @Bean
	// public PaymentService paymentService() {
	// 	return new PaymentService(cachedExRateProvider());
	// }
	//
	// @Bean
	// public ExRateProvider cachedExRateProvider() {
	// 	return new CacheExRateProvider(exRateProvider());
	// }

	@Bean
	public PaymentService paymentService() {
		return new PaymentService(exRateProvider(), clock());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new ExRateProviderStub(BigDecimal.valueOf(1000));
	}

	@Bean
	public Clock clock() {
		return Clock.fixed(Instant.now(), ZoneId.systemDefault());
	}
}
