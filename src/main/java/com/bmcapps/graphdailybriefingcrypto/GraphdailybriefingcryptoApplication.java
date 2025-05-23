package com.bmcapps.graphdailybriefingcrypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GraphdailybriefingcryptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphdailybriefingcryptoApplication.class, args);
	}

}
