package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.repository.jpa.NsInstantiatedBaseJpa;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.graph.vnfm.AbstractTaskUow;
import com.ubiqube.etsi.mano.service.vim.Vim;

public abstract class AbstractNsTaskUow extends Task<NsUnitOfWork, String> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AbstractTaskUow.class);

	private final VimConnectionInformation vimConnectionInformation;

	private final transient Vim vim;

	private final NsUnitOfWork uaow;

	private final Map<String, String> context;

	private final transient NsInstantiatedBaseJpa resourceHandleEntityJpa;

	private final transient Function<Parameters, String> function;

	public AbstractNsTaskUow(final VimConnectionInformation vimConnectionInformation, final Vim vim, final NsUnitOfWork uaow, final NsInstantiatedBaseJpa _resourceHandleEntityJpa, final Map<String, String> _context, final boolean _create, final VnfmInterface vnfm) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		this.uaow = uaow;
		context = _context;
		resourceHandleEntityJpa = _resourceHandleEntityJpa;
		if (_create) {
			function = x -> {
				final String res = uaow.exec(vimConnectionInformation, vnfm, vim, context);
				if (null != res) {
					context.put(uaow.getToscaName(), res);
					LOG.debug("Adding to context: {} => {}", uaow.getName(), res);
					uaow.getResourceHandleEntity().setResourceId(res);
				}
				return res;
			};
		} else {
			function = x -> uaow.rollback(x.vimConnectionInformationLocal, null, x.vimLocal, x.resourceId, x.contextLocal);
		}
	}

	@Override
	public final String execute() {
		RuntimeException eRoot = null;
		final NsInstantiatedBase resource = this.uaow.getResourceHandleEntity();
		resource.setStartTime(new Date());
		resource.setChangeResult(InstantiationStatusType.STARTED);
		try {
			LOG.info("Task {} Started.", uaow.getName());
			function.apply(new Parameters(vimConnectionInformation, vim, context, resource.getResourceId()));
			resource.setChangeResult(InstantiationStatusType.SUCCESS);
		} catch (final RuntimeException e) {
			LOG.warn("Task {} failed.", uaow.getName(), e);
			eRoot = e;
			resource.setChangeResult(InstantiationStatusType.FAILED);
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
