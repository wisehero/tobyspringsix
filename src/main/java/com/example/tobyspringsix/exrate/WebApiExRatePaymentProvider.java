package com.example.tobyspringsix.exrate;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.example.tobyspringsix.api.ApiTemplate;
import com.example.tobyspringsix.api.ErApiExtractor;
import com.example.tobyspringsix.api.HttpClientApiExecutor;
import com.example.tobyspringsix.payment.ExRateProvider;

@Component
public class WebApiExRatePaymentProvider implements ExRateProvider {

	private final ApiTemplate apiTemplate = new ApiTemplate();

	public BigDecimal getExRate(String currency) {
		String url = "https://open.er-api.com/v6/latest/" + currency;
		return apiTemplate.getForExRate(url, new HttpClientApiExecutor(), new ErApiExtractor());
	}

}
