package com.example.tobyspringsix.api;

import java.math.BigDecimal;

import com.example.tobyspringsix.exrate.ExRateData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErApiExtractor implements ExRateExtractor {

	@Override
	public BigDecimal extract(String response) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		ExRateData data = objectMapper.readValue(response, ExRateData.class);
		return data.rates().get("KRW");
	}
}
