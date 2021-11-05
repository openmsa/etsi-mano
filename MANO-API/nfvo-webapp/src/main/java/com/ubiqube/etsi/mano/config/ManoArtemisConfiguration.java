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

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@org.springframework.context.annotation.Configuration
public class ManoArtemisConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(ManoArtemisConfiguration.class);

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
