package com.ubiqube.etsi.mano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jms.annotation.EnableJms;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJms
public class Application extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
