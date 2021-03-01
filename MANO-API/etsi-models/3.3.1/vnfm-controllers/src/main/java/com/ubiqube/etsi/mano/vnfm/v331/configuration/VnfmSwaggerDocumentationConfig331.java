package com.ubiqube.etsi.mano.vnfm.v331.configuration;

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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-19T11:42:02.797+02:00")

@Configuration
public class VnfmSwaggerDocumentationConfig331 {
	@Value("${server.servlet.contextPath}")
	private String contextPath;

	ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("ETSI SOL002 & SOL003 & SOL005 API")
				.description("ETSI MANO API")
				.license("ETSI Forge copyright notice")
				.licenseUrl("https://forge.etsi.org/etsi-forge-copyright-notice.txt")
				.termsOfServiceUrl("")
				.version("3.3.1-impl:etsi.org:ETSI_NFV_OpenAPI:1")
				.contact(new Contact("", "", ""))
				.build();
	}

	@Bean
	public Docket customImplementationVnfm331() {
		return new Docket(DocumentationType.SWAGGER_2)
				.pathProvider(new CustomPathProvider())
				.groupName("vnfm-etsi-mano-3.3.1")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ubiqube.etsi.mano.vnfm.v331"))
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
