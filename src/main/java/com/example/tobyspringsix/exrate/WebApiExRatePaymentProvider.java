package com.example.tobyspringsix.exrate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.tobyspringsix.api.SimpleApiExecutor;
import com.example.tobyspringsix.payment.ExRateProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WebApiExRatePaymentProvider implements ExRateProvider {

	public BigDecimal getExRate(String currency) {
		String url = "https://open.er-api.com/v6/latest/" + currency;
		return runApiForExRate(url);
	}

	private static BigDecimal runApiForExRate(String url) {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		String response;
		try {
			response = new SimpleApiExecutor().execute(uri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try {
			return extractExRate(response);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private static BigDecimal extractExRate(String response) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		ExRateData data = objectMapper.readValue(response, ExRateData.class);
		return data.rates().get("KRW");
	}

	private static String executeApi(URI uri) throws IOException {
		String response;
		HttpURLConnection connection = (HttpURLConnection)uri.toURL().openConnection();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			response = br.lines().collect(Collectors.joining());
		}
		return response;
	}
}
