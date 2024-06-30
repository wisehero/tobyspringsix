package com.example.tobyspringsix;

import java.math.BigDecimal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.tobyspringsix.payment.ExRateProvider;
import com.example.tobyspringsix.payment.ExRateProviderStub;
import com.example.tobyspringsix.payment.PaymentService;

@Configuration
public class TestObjectFactory {

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
		return new PaymentService(exRateProvider());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new ExRateProviderStub(BigDecimal.valueOf(1000));
	}
}
