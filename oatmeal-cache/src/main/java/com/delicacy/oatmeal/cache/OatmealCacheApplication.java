package com.delicacy.oatmeal.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.delicacy")
public class OatmealCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(OatmealCacheApplication.class, args);
	}
}
