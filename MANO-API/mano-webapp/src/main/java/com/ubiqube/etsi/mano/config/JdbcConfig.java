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
package com.ubiqube.etsi.mano.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JdbcConfig {

	@Bean
	public static DataSource sqlDataSource(final com.ubiqube.etsi.mano.service.Configuration _conf) {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(_conf.build("jdbc.url").notNull().build());
		dataSource.setUsername(_conf.get("jdbc.username"));
		dataSource.setPassword(_conf.get("jdbc.password"));

		return dataSource;
	}
}
