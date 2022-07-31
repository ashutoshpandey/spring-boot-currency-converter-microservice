package com.microservice.currencyconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients("com.microservice.currencyconverter")
public class CurrencyConverterMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterMicroserviceApplication.class, args);
	}

}
