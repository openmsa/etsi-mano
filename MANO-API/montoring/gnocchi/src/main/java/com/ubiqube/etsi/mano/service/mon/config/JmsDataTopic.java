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
package com.ubiqube.etsi.mano.service.mon.config;

import javax.jms.ConnectionFactory;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Configuration
public class JmsDataTopic {

	@Bean
	public JmsListenerContainerFactory<?> gnocchiDataFactory(final ConnectionFactory connectionFactory, final DefaultJmsListenerContainerFactoryConfigurer configurer) {
		final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all boot's default to this factory, including the message converter
		configurer.configure(factory, connectionFactory);
		// You could still override some of Boot's default if necessary.
		factory.setPubSubDomain(true);
		return factory;
	}

	@Primary
	@Bean
	public JmsTemplate jmsQueueTemplate(final ConnectionFactory connectionFactory, final MessageConverter messageConverter) {
		final JmsTemplate jt = new JmsTemplate(connectionFactory);
		jt.setPubSubDomain(false);
		jt.setMessageConverter(messageConverter);
		return jt;
	}

	@Bean
	public JmsTemplate jmsTopicTemplate(final ConnectionFactory connectionFactory, final MessageConverter messageConverter) {
		final JmsTemplate jt = new JmsTemplate(connectionFactory);
		jt.setPubSubDomain(true);
		jt.setMessageConverter(messageConverter);
		return jt;
	}

	@Bean
	public com.fasterxml.jackson.databind.Module dateTimeModule() {
		return new JavaTimeModule();
	}
}
