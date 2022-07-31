package com.microservice.currencyconverter.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.microservice.currencyconverter.beans.CurrencyConverterBean;

@FeignClient(name="currency-exchange-service", url="localhost:8001")
public interface CurrencyExchangeServiceProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConverterBean convertCurrency(
            @PathVariable String from,
            @PathVariable String to);
}
