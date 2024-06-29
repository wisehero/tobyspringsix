package com.example.tobyspringsix.exrate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.tobyspringsix.payment.ExRateProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WebApiExRatePaymentProvider implements ExRateProvider {

	public BigDecimal getExRate(String currency) throws IOException {
		URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String response = bufferedReader.lines().collect(Collectors.joining());
		bufferedReader.close();

		ObjectMapper objectMapper = new ObjectMapper();
		ExRateData data = objectMapper.readValue(response, ExRateData.class);

		System.out.println("API ExRate: " + data.rates().get("KRW"));
		return data.rates().get("KRW");
	}
}
