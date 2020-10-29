/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.vnfm.v261;

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
public class SwaggerDocumentationConfigVnfm261 {
	@Value("${server.servlet.contextPath}")
	private String contextPath;

	public static ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("ETSI SOL002 & SOL003 API")
				.description("ETSI MANO API")
				.license("ETSI Forge copyright notice")
				.licenseUrl("https://forge.etsi.org/etsi-forge-copyright-notice.txt")
				.termsOfServiceUrl("")
				.version("2.6.1-impl:etsi.org:ETSI_NFV_OpenAPI:1")
				.contact(new Contact("", "", ""))
				.build();
	}

	@Bean
	public Docket customImplementationVnfm261() {
		return new Docket(DocumentationType.SWAGGER_2)
				.pathProvider(new CustomPathProvider())
				.groupName("vnfm-etsi-mano-2.6.1")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ubiqube.etsi.mano.vnfm.v261"))
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
