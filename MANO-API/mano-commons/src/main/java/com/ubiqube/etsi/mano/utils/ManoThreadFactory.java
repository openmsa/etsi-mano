package com.ubiqube.etsi.mano.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ManoThreadFactory implements ThreadFactory {
	private static final String THREAD_GROUP_PREFIX = "Mano thread pool: ";
	private final ThreadGroup group;
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;

	public ManoThreadFactory(final String groupname) {
		final SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		namePrefix = THREAD_GROUP_PREFIX + groupname + "-";
	}

	@Override
	public Thread newThread(final Runnable r) {
		return new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
	}

}
