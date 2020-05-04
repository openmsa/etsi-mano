package com.ubiqube.etsi.mano.service.graph;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.ResourceHandleEntityJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;

public abstract class AbstractTaskUow extends Task<UnitOfWork, String> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AbstractTaskUow.class);

	private final VimConnectionInformation vimConnectionInformation;

	private final transient Vim vim;

	private final UnitOfWork uaow;

	private final Map<String, String> context;

	private final transient ResourceHandleEntityJpa resourceHandleEntityJpa;

	private final transient Function<Parameters, String> function;

	public AbstractTaskUow(final VimConnectionInformation vimConnectionInformation, final Vim vim, final UnitOfWork uaow, final ResourceHandleEntityJpa _resourceHandleEntityJpa, final Map<String, String> _context, final boolean _create) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		this.uaow = uaow;
		context = _context;
		resourceHandleEntityJpa = _resourceHandleEntityJpa;
		if (_create) {
			function = x -> {
				final Optional<String> res = Optional.ofNullable(uaow.exec(vimConnectionInformation, vim, context));
				res.ifPresent(obj -> {
					context.put(uaow.getToscaName(), obj);
					LOG.debug("Adding to context: {} => {}", uaow.getName(), obj);
					uaow.getResourceHandleEntity().setResourceId(obj);
				});
				return res.get();
			};
		} else {
			function = x -> uaow.rollback(x.vimConnectionInformationLocal, x.vimLocal, x.contextLocal);
		}
	}

	@Override
	public final String execute() {
		RuntimeException eRoot = null;
		final ResourceHandleEntity resource = this.uaow.getResourceHandleEntity();
		resource.setStartTime(new Date());
		resource.setStatus(InstantiationStatusType.STARTED);
		resourceHandleEntityJpa.save(resource);
		try {
			LOG.info("Task {} Started.", uaow.getName());
			function.apply(new Parameters(vimConnectionInformation, vim, context));
			resource.setStatus(InstantiationStatusType.SUCCESS);
		} catch (final RuntimeException e) {
			eRoot = e;
			resource.setStatus(InstantiationStatusType.FAILED);
		}
		LOG.info("Task {} Finished.", uaow.getName());
		resource.setEndTime(new Date());
		resourceHandleEntityJpa.save(resource);
		if (eRoot != null) {
			throw eRoot;
		}
		return null;
	}

	class Parameters {
		public Parameters(final VimConnectionInformation _vimConnectionInformation, final Vim _vim, final Map<String, String> _context) {
			vimConnectionInformationLocal = _vimConnectionInformation;
			vimLocal = _vim;
			contextLocal = _context;
		}

		private final VimConnectionInformation vimConnectionInformationLocal;
		private final Vim vimLocal;
		private final Map<String, String> contextLocal;
	}
}
