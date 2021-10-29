package com.ubiqube.etsi.mano.vnfm.v351;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Configuration
public class VnfmSwaggerDocumentationConfig351 {
	@SuppressWarnings("static-method")
	@Bean
	public GroupedOpenApi customImplementationVnfm351() {
		return GroupedOpenApi.builder()
				.group("vnfm-etsi-mano-3.5.1")
				.packagesToScan("com.ubiqube.etsi.mano.vnfm.v351")
				.build();
	}
}
