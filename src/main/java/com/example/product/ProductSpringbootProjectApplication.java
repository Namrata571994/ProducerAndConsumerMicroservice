package com.example.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class ProductSpringbootProjectApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ProductSpringbootProjectApplication.class, args);
		String[] beanDefinitionNames = configurableApplicationContext.getBeanDefinitionNames();
		Arrays.stream(beanDefinitionNames).sorted().forEach(System.out::println);
	}

}



