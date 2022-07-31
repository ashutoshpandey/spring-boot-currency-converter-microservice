package com.microservice.currencyconverter.controllers;

import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;

import com.microservice.currencyconverter.service.CurrencyExchangeServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.currencyconverter.beans.CurrencyConverterBean;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConverterController {
    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConverterBean convertCurrency(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity){
        ResponseEntity<CurrencyConverterBean> responseEntity = getConversion(from, to);
        CurrencyConverterBean currencyConverterBean = responseEntity.getBody();

        if(currencyConverterBean != null) {
            return new CurrencyConverterBean(
                    currencyConverterBean.getId(),
                    from,
                    to,
                    quantity,
                    quantity.multiply(currencyConverterBean.getConversionMultiple()),
                    currencyConverterBean.getConversionMultiple(),
                    currencyConverterBean.getPort()
            );
        }else{
            return null;
        }
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConverterBean convertCurrency1(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity){
        CurrencyConverterBean currencyConverterBean = getConversionFromProxy(from, to);

        if(currencyConverterBean != null) {
            return new CurrencyConverterBean(
                    currencyConverterBean.getId(),
                    from,
                    to,
                    quantity,
                    quantity.multiply(currencyConverterBean.getConversionMultiple()),
                    currencyConverterBean.getConversionMultiple(),
                    currencyConverterBean.getPort()
            );
        }else{
            return null;
        }
    }

    private ResponseEntity<CurrencyConverterBean> getConversion(String from, String to){
        Map<String, String> uriVariables = new HashMap<>();

        uriVariables.put("to", to);
        uriVariables.put("from", from);

        return new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}", CurrencyConverterBean.class, uriVariables);
    }

    private CurrencyConverterBean getConversionFromProxy(String from, String to){
        return currencyExchangeServiceProxy.convertCurrency(from, to);
    }
}
