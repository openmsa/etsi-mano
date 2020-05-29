package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;

public abstract class AbstractTaskUow extends Task<UnitOfWork, String> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AbstractTaskUow.class);

	private final VimConnectionInformation vimConnectionInformation;

	private final transient Vim vim;

	private final UnitOfWork uaow;

	private final Map<String, String> context;

	private final transient VnfLiveInstanceJpa vnfLiveInstanceJpa;

	private final transient Function<Parameters, String> function;

	public AbstractTaskUow(final VimConnectionInformation vimConnectionInformation, final Vim vim, final UnitOfWork uaow, final VnfLiveInstanceJpa _vnfLiveInstanceJpa, final Map<String, String> _context, final boolean _create) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		this.uaow = uaow;
		context = _context;
		vnfLiveInstanceJpa = _vnfLiveInstanceJpa;
		if (_create) {
			function = x -> {
				final String res = uaow.exec(vimConnectionInformation, vim, context);
				if (null != res) {
					context.put(uaow.getToscaName(), res);
					LOG.debug("Adding to context: {} => {}", uaow.getName(), res);
					uaow.getResourceHandleEntity().setResourceId(res);
				}
				return res;
			};
		} else {
			function = x -> uaow.rollback(x.vimConnectionInformationLocal, x.vimLocal, x.resourceId, x.contextLocal);
		}
	}

	@Override
	public final String execute() {
		RuntimeException eRoot = null;
		final VnfInstantiatedBase resource = this.uaow.getResourceHandleEntity();
		resource.setStartTime(new Date());
		resource.setStatus(InstantiationStatusType.STARTED);
		try {
			LOG.info("Task {} Started.", uaow.getName());
			function.apply(new Parameters(vimConnectionInformation, vim, context, resource.getResourceId()));
			resource.setStatus(InstantiationStatusType.SUCCESS);
		} catch (final RuntimeException e) {
			LOG.warn("Task {} failed.", uaow.getName(), e);
			eRoot = e;
			resource.setStatus(InstantiationStatusType.FAILED);
		}
		LOG.info("Task {} Finished.", uaow.getName());
		resource.setEndTime(new Date());
		if (eRoot != null) {
			throw eRoot;
		}
		return null;
	}

	class Parameters {
		public Parameters(final VimConnectionInformation _vimConnectionInformation, final Vim _vim, final Map<String, String> _context, final String _resourceId) {
			vimConnectionInformationLocal = _vimConnectionInformation;
			vimLocal = _vim;
			contextLocal = _context;
			resourceId = _resourceId;
		}

		private final VimConnectionInformation vimConnectionInformationLocal;
		private final Vim vimLocal;
		private final Map<String, String> contextLocal;
		private final String resourceId;
	}
}
