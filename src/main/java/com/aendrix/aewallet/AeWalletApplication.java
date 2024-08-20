package com.aendrix.aewallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.aendrix.aewallet"})
public class AeWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(AeWalletApplication.class, args);
	}

}
