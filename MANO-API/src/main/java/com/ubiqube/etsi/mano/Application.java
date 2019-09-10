package com.ubiqube.etsi.mano;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application extends SpringBootServletInitializer {
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
