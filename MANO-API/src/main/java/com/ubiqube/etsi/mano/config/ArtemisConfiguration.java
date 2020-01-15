package com.ubiqube.etsi.mano.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.ubiqube.etsi.mano.service.Configuration;

@org.springframework.context.annotation.Configuration
public class ArtemisConfiguration {
	@Bean
	public ConnectionFactory activeMQJMSConnectionFactory(final Configuration configuration) {
		final ActiveMQJMSConnectionFactory activeMQJMSConnectionFactory = new ActiveMQJMSConnectionFactory((String) configuration.build("jms.artemis.url").withDefault("localhost").build());
		activeMQJMSConnectionFactory.setUser(configuration.get("jms.artemis.user"));
		activeMQJMSConnectionFactory.setPassword(configuration.get("jms.artemis.password"));
		return activeMQJMSConnectionFactory;
	}

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
}
