/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
