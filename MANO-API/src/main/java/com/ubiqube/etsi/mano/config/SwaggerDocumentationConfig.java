package com.ubiqube.etsi.mano.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerDocumentationConfig {
	ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("ETSI SOL002 & SOL003 & SOL005 API")
				.description("ETSI MANO API")
				.license("ETSI Forge copyright notice")
				.licenseUrl("https://forge.etsi.org/etsi-forge-copyright-notice.txt")
				.termsOfServiceUrl("")
				.version("1.3.0-impl:etsi.org:ETSI_NFV_OpenAPI:1")
				.contact(new Contact("", "", ""))
				.build();
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ubiqube.etsi.mano"))
				.build()
				.directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
				.apiInfo(apiInfo());
	}

}
