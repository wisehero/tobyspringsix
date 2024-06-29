package com.example.tobyspringsix;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.tobyspringsix.exrate.CacheExRateProvider;
import com.example.tobyspringsix.exrate.WebApiExRatePaymentProvider;
import com.example.tobyspringsix.payment.ExRateProvider;
import com.example.tobyspringsix.payment.PaymentService;

@Configuration
public class ObjectFactory {

	@Bean
	public PaymentService paymentService() {
		return new PaymentService(cachedExRateProvider());
	}

	@Bean
	public ExRateProvider cachedExRateProvider() {
		return new CacheExRateProvider(exRateProvider());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new WebApiExRatePaymentProvider();
	}
}
