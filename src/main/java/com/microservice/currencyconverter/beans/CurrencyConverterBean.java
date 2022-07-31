package com.microservice.currencyconverter.beans;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CurrencyConverterBean {
    private Long id;
    private Integer port;
    private String toCurrency;
    private String fromCurrency;
    private BigDecimal quantity;
    private BigDecimal totalAmount;
    private BigDecimal conversionMultiple;

    public CurrencyConverterBean(){}

    public CurrencyConverterBean(
        Long id,
        String fromCurrency,
        String toCurrency,
        BigDecimal quantity,
        BigDecimal totalAmount,
        BigDecimal conversionMultiple,
        Integer port
    ){
        this.id = id;
        this.quantity = quantity;
        this.toCurrency = toCurrency;
        this.totalAmount = totalAmount;
        this.fromCurrency = fromCurrency;
        this.conversionMultiple = conversionMultiple;
        this.port = port;
    }
}
