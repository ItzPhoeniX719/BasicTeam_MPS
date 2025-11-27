package com.example.currency.controller;

import com.example.currency.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        addCommonAttributes(model, null, null, null, null, null);
        return "index";
    }

    @GetMapping("/convert")
    public String convert(@RequestParam double amount,
                          @RequestParam String from,
                          @RequestParam String to,
                          Model model) {

        double rate = currencyService.getRate(from, to);
        double result = currencyService.convert(amount, from, to);

        addCommonAttributes(model, amount, from, to, rate, result);
        return "index";
    }

    private void addCommonAttributes(Model model,
                                     Double amount,
                                     String from,
                                     String to,
                                     Double rate,
                                     Double result) {

        List<String> currencies = Arrays.asList("EUR", "USD", "GBP", "JPY", "PESETAS");

        model.addAttribute("currencies", currencies);
        model.addAttribute("amount", amount);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("rate", rate);
        model.addAttribute("result", result);
    }
}
