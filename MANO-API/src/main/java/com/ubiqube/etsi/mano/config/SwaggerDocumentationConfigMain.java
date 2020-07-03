package com.ubiqube.etsi.mano.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.DefaultPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerDocumentationConfigMain {
	@Value("${server.servlet.contextPath}")
	private String contextPath;

	public static ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("ETSI SOL005 API")
				.description("ETSI MANO API")
				.license("ETSI Forge copyright notice")
				.licenseUrl("https://forge.etsi.org/etsi-forge-copyright-notice.txt")
				.termsOfServiceUrl("")
				.version("2.6.1-impl:etsi.org:ETSI_NFV_OpenAPI:1")
				.contact(new Contact("", "", ""))
				.build();
	}

	@Bean
	public Docket swaggerMainDocumentation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.pathProvider(new CustomPathProvider())
				.groupName("main")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ubiqube.etsi.mano.controller"))
				.build()
				.directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
				.apiInfo(apiInfo());
	}

	public class CustomPathProvider extends DefaultPathProvider {
		@Override
		public String getOperationPath(final String op) {
			String operationPath = op;
			if (operationPath.startsWith(contextPath)) {
				operationPath = operationPath.substring(contextPath.length());
			}
			return Paths.removeAdjacentForwardSlashes(UriComponentsBuilder.newInstance().replacePath(operationPath).build().toString());
		}
	}
}
