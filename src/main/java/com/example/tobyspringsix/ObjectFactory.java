package com.example.tobyspringsix;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.tobyspringsix")
public class ObjectFactory {

	// @Bean
	// public PaymentService paymentService() {
	// 	return new PaymentService(exRateProvider());
	// }
	//
	// @Bean
	// public ExRateProvider exRateProvider() {
	// 	return new SimpleExRateProvider();
	// }
}
