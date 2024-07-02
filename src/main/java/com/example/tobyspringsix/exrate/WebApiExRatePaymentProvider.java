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

import com.example.tobyspringsix.payment.ExRateProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WebApiExRatePaymentProvider implements ExRateProvider {

	public BigDecimal getExRate(String currency) {
		URI uri = null;
		try {
			uri = new URI("https://open.er-api.com/v6/latest/" + currency);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		String response;
		try {
			HttpURLConnection connection = (HttpURLConnection)uri.toURL().openConnection();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				response = br.lines().collect(Collectors.joining());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ExRateData data = objectMapper.readValue(response, ExRateData.class);
			System.out.println("API ExRate: " + data.rates().get("KRW"));
			return data.rates().get("KRW");
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
