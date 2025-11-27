package com.example.currency.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyService {

    // Tipos de cambio respecto al EUR (ejemplo simple)
    private final Map<String, Double> ratesFromEur = new HashMap<>();

    public CurrencyService() {
        ratesFromEur.put("EUR", 1.0);
        ratesFromEur.put("USD", 1.08);
        ratesFromEur.put("GBP", 0.86);
        ratesFromEur.put("JPY", 170.0);
    }

    public double getRate(String from, String to) {
        Double fromRate = ratesFromEur.get(from);
        Double toRate = ratesFromEur.get(to);

        if (fromRate == null || toRate == null) {
            throw new IllegalArgumentException("Divisa no soportada");
        }

        return toRate / fromRate;
    }

    public double convert(double amount, String from, String to) {
        return amount * getRate(from, to);
    }
}
