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

import java.util.Arrays;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ubiqube.etsi.mano.config.properties.ManoProperties;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerDocumentationConfigMain {
	private final SecutiryConfig secutiryConfig;

	public SwaggerDocumentationConfigMain(final SecutiryConfig secutiryConfig) {
		super();
		this.secutiryConfig = secutiryConfig;
	}

	@Bean
	public OpenAPI OpenApiMain(final ManoProperties oauth2Params) {
		final OpenAPI openApi = new OpenAPI();
		final SecurityScheme sec = secutiryConfig.getSwaggerSecurityScheme(oauth2Params);
		if (null != sec) {
			openApi.components(new Components()
					.addSecuritySchemes("bearer-jwt", sec))
					.addSecurityItem(new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
		}
		openApi.info(new Info().title("ETSI Main NFVO API")
				.description("ETSI MANO SOL003 & SOL005 API")
				.license(new License().name("ETSI Forge copyright notice").url("https://forge.etsi.org/etsi-forge-copyright-notice.txt"))
				.version("1.0.0-impl:etsi.org:ETSI_NFV_OpenAPI:1"));
		return openApi;
	}

	@SuppressWarnings("static-method")
	@Bean
	public GroupedOpenApi customImplementationMain() {
		return GroupedOpenApi.builder()
				.group("main")
				.packagesToScan("com.ubiqube.etsi.mano.controller")
				.build();
	}
}
