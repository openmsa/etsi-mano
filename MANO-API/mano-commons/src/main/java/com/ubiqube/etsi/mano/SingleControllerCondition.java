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
package com.ubiqube.etsi.mano;

import java.util.Optional;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.exception.GenericException;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SingleControllerCondition extends SpringBootCondition implements ConfigurationCondition {

	private static final Logger LOG = LoggerFactory.getLogger(SingleControllerCondition.class);

	@Override
	public ConditionOutcome getMatchOutcome(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
		final ConditionMessage matchMessage = ConditionMessage.empty();
		final MergedAnnotations annotations = metadata.getAnnotations();
		final ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		final Object src = getSource(annotations);
		final Class<?> clazz = buildClass(src);
		final RequestMapping srcAnnotation = AnnotationUtils.findAnnotation(clazz, RequestMapping.class);
		final String[] beans = Optional.ofNullable(beanFactory).map(x -> x.getBeanNamesForAnnotation(RestController.class)).orElseGet(() -> new String[0]);
		for (final String bean : beans) {
			final Class<?> type = Optional.ofNullable(beanFactory).map(x -> x.getType(bean)).orElseThrow();
			final RequestMapping dstAnnotation = AnnotationUtils.findAnnotation(type, RequestMapping.class);
			if (!isMatching(srcAnnotation, dstAnnotation)) {
				continue;
			}
			matchMessage.append(" Not initializing because " + type + " have the same values.");
			LOG.warn("Ignoring {} because {} match.", src, type);
			return ConditionOutcome.noMatch(matchMessage);
		}
		LOG.info("Bean is matching {}", clazz);
		return ConditionOutcome.match(matchMessage);
	}

	private static Object getSource(final MergedAnnotations annotations) {
		final MergedAnnotation<RestController> anSrc = annotations.get(RestController.class);
		final Object src = anSrc.getSource();
		if (null == src) {
			throw new GenericException("Bad annotation a controller.");
		}
		return src;
	}

	@NotNull
	private static Class<?> buildClass(final Object src) {
		try {
			return Class.forName(src.toString());
		} catch (final RuntimeException | ClassNotFoundException e) {
			throw new GenericException(e);
		}
	}

	private static boolean isMatching(final RequestMapping annSrc, @Nullable final RequestMapping ann) {
		if (ann == null) {
			return false;
		}
		return atLeastOne(annSrc.value(), ann.value()) && atLeastOne(annSrc.headers(), ann.headers());
	}

	private static boolean atLeastOne(final String[] src, final String[] dst) {
		for (final String strSrc : src) {
			for (final String strDst : dst) {
				if (strSrc.equals(strDst)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public ConfigurationPhase getConfigurationPhase() {
		return ConfigurationPhase.REGISTER_BEAN;
	}

}
