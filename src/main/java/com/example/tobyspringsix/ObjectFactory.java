package com.example.tobyspringsix;

public class ObjectFactory {
	public PaymentService paymentService() {
		return new PaymentService(exRateProvider());
	}

	public ExRateProvider exRateProvider() {
		return new WebApiExRatePaymentProvider();
	}
}
