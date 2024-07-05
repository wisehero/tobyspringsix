package com.example.tobyspringsix.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Component;

import com.example.tobyspringsix.api.ApiExecutor;
import com.example.tobyspringsix.api.ErApiExtractor;
import com.example.tobyspringsix.api.ExRateExtractor;
import com.example.tobyspringsix.api.SimpleApiExecutor;
import com.example.tobyspringsix.payment.ExRateProvider;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class WebApiExRatePaymentProvider implements ExRateProvider {

	public BigDecimal getExRate(String currency) {
		String url = "https://open.er-api.com/v6/latest/" + currency;
		return runApiForExRate(url, new SimpleApiExecutor(), new ErApiExtractor());
	}

	private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		String response;
		try {
			response = apiExecutor.execute(uri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try {
			return exRateExtractor.extract(response);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
