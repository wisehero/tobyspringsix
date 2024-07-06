package com.example.tobyspringsix;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.tobyspringsix.api.ApiTemplate;
import com.example.tobyspringsix.api.ErApiExtractor;
import com.example.tobyspringsix.api.SimpleApiExecutor;
import com.example.tobyspringsix.exrate.WebApiExRatePaymentProvider;
import com.example.tobyspringsix.payment.ExRateProvider;
import com.example.tobyspringsix.payment.PaymentService;

@Configuration
public class PaymentConfig {

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
	public ApiTemplate apiTemplate() {
		return new ApiTemplate(new SimpleApiExecutor(), new ErApiExtractor());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new WebApiExRatePaymentProvider(apiTemplate());
	}

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}
}
