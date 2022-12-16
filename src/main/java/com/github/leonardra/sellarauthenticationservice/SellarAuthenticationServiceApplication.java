package com.github.leonardra.sellarauthenticationservice;

import com.github.leonardra.sellarauthenticationservice.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class SellarAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellarAuthenticationServiceApplication.class, args);
	}

}
