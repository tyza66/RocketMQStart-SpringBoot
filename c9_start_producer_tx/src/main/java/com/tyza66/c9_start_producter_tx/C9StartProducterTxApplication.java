package com.tyza66.c9_start_producter_tx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class C9StartProducterTxApplication {

	public static void main(String[] args) {
		SpringApplication.run(C9StartProducterTxApplication.class, args);
	}

}
