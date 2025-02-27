package com.scone.DeCypher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

@SpringBootApplication
public class DeCypherApplication {

	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		SpringApplication.run(DeCypherApplication.class, args);
	}

}
