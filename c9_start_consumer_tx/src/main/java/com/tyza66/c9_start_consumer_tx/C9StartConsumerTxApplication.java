package com.tyza66.c9_start_consumer_tx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class C9StartConsumerTxApplication {

	public static void main(String[] args) {
		SpringApplication.run(C9StartConsumerTxApplication.class, args);
	}

}
