package com.ubiqube.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.ExecutionResults;
import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;

public class SleepyTaskProvider implements TaskProvider<UnitOfWork, Integer> {

	private static final Logger LOG = LoggerFactory.getLogger(SleepyTaskProvider.class);

	@Override
	public Task<UnitOfWork, Integer> provideTask(final UnitOfWork id) {
		LOG.debug("Called with: {}" + id);
		return new Task<UnitOfWork, Integer>() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Integer execute() {
				final ExecutionResults<UnitOfWork, Integer> res = getParentResults();
				LOG.debug("Execute {}", id);
				try {
					// id.exec(null, null);
				} catch (final Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		};
	}

}
