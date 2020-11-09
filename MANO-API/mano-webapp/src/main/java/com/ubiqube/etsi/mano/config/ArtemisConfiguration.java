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

import javax.jms.ConnectionFactory;

import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.ubiqube.etsi.mano.service.Configuration;

@org.springframework.context.annotation.Configuration
public class ArtemisConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(ArtemisConfiguration.class);

	@Bean
	public static ConnectionFactory activeMQJMSConnectionFactory(final Configuration configuration) {
		final ActiveMQJMSConnectionFactory activeMQJMSConnectionFactory = new ActiveMQJMSConnectionFactory((String) configuration.build("jms.artemis.url").withDefault("localhost").build());
		activeMQJMSConnectionFactory.setUser(configuration.get("jms.artemis.user"));
		activeMQJMSConnectionFactory.setPassword(configuration.get("jms.artemis.password"));
		activeMQJMSConnectionFactory.setIgnoreJTA(true);
		return activeMQJMSConnectionFactory;
	}

	@Bean
	public static MessageConverter jacksonJmsMessageConverter() {
		final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Bean
	public static JmsListenerContainerFactory<DefaultMessageListenerContainer> jmsListenerContainerFactory(final ConnectionFactory connectionFactory, final MessageConverter messageConverter) {
		final DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
		jmsListenerContainerFactory.setConcurrency("5-10");
		jmsListenerContainerFactory.setSessionTransacted(Boolean.FALSE);
		jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
		jmsListenerContainerFactory.setMessageConverter(messageConverter);
		return jmsListenerContainerFactory;
	}

	@Bean
	public static DefaultMessageListenerContainer defaultMessageListenerContainer(final ConnectionFactory connectionFactory) {
		LOG.warn("Using our instance of defaultMessageListenerContainer");
		final DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
		defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
		defaultMessageListenerContainer.setDestinationName("etsi-mano-default");
		defaultMessageListenerContainer.setConcurrency("1-5");
		defaultMessageListenerContainer.setSessionTransacted(false);
		return defaultMessageListenerContainer;
	}
}
