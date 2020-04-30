package com.ubiqube.etsi.mano.service.graph;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.ResourceHandleEntityJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class UowTask extends Task<UnitOfWork, String> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(UowTask.class);

	private final VimConnectionInformation vimConnectionInformation;

	private final transient Vim vim;

	private final UnitOfWork uaow;

	private final Map<String, String> context;

	private final ResourceHandleEntityJpa resourceHandleEntityJpa;

	public UowTask(final VimConnectionInformation vimConnectionInformation, final Vim vim, final UnitOfWork uaow, final ResourceHandleEntityJpa _resourceHandleEntityJpa, final Map<String, String> _context) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		this.uaow = uaow;
		context = _context;
		resourceHandleEntityJpa = _resourceHandleEntityJpa;
	}

	@Override
	public String execute() {
		RuntimeException eRoot = null;
		Optional<String> res = Optional.empty();
		final ResourceHandleEntity resource = this.uaow.getResourceHandleEntity();
		try {
			LOG.info("Task {} Started.", uaow.getName());
			res = Optional.ofNullable(uaow.exec(vimConnectionInformation, vim, context));
			res.ifPresent(x -> {
				context.put(uaow.getName(), x);
				LOG.debug("Adding to context: {} => {}", uaow.getName(), x);
				resource.setResourceId(x);
			});
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
		return res.orElse(null);
	}

}
