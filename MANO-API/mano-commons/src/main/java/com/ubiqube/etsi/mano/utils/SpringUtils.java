package com.ubiqube.etsi.mano.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.servlet.ServletRequest;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerMapping;

public final class SpringUtils {

	private SpringUtils() {
		// Nothing
	}

	/**
	 * Should be removed. Should match {*attr}
	 *
	 * @see https://www.baeldung.com/spring-5-mvc-url-matching
	 * @param request
	 * @return
	 */
	@Nonnull
	public static String extractParams(final ServletRequest request) {
		final String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		final String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

		return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
	}

	public static ThreadPoolExecutor getFixedThreadPool(final int corePoolSize, final String groupname) {
		return new ThreadPoolExecutor(corePoolSize, corePoolSize, 0L,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1000), new ManoThreadFactory(groupname));
	}
}
