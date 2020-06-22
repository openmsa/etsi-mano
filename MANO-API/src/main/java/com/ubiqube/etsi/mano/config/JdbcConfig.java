package com.ubiqube.etsi.mano.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JdbcConfig {

	@Bean
	public DataSource sqlDataSource(final com.ubiqube.etsi.mano.service.Configuration _conf) {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(_conf.build("jdbc.url").notNull().build());
		dataSource.setUsername(_conf.get("jdbc.username"));
		dataSource.setPassword(_conf.get("jdbc.password"));

		return dataSource;
	}
}
