package com.ubiqube.etsi.mano.config;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Jersey configuration. With Multi-part for document upload, and annotation
 * package scan.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Configuration
public class JerseyConfig extends ResourceConfig {

	public static void main(String[] args) {
		SpringApplication.run(JerseyConfig.class, args);
	}

	public JerseyConfig() {
		register(MultiPartFeature.class);
		packages("com.ubiqube.etsi.mano");
	}
}
