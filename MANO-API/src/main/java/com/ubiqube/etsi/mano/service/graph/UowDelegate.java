package com.ubiqube.etsi.mano.service.graph;

import java.util.Date;
import java.util.Map;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.InstanciatedResource;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.InstanciatedResourceJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 * Manage the DB iinstantiation state.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class UowDelegate implements UnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final UnitOfWork delegate;

	protected final InstanciatedResource instanciatedResource;
	private final InstanciatedResourceJpa instanciatedResourceJpa;

	public UowDelegate(@Nonnull final InstanciatedResource _instanciatedResource, final InstanciatedResourceJpa _instanciatedResourceJpa, @Nonnull final UnitOfWork _uow) {
		instanciatedResource = _instanciatedResource;
		delegate = _uow;
		instanciatedResourceJpa = _instanciatedResourceJpa;
	}

	@Override
	public String getName() {
		return delegate.getName();
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		instanciatedResource.setStartTime(new Date());
		instanciatedResource.setStatus(InstantiationStatusType.STARTED);
		instanciatedResourceJpa.save(instanciatedResource);
		try {
			final String vimResourceId = delegate.exec(vimConnectionInformation, vim, context);
			instanciatedResource.setVimResourceId(vimResourceId);
			instanciatedResource.setEndTime(new Date());
			instanciatedResourceJpa.save(instanciatedResource);
			return vimResourceId;
		} catch (final RuntimeException e) {
			instanciatedResource.setStatus(InstantiationStatusType.FAILED);
			instanciatedResource.setEndTime(new Date());
			instanciatedResourceJpa.save(instanciatedResource);
			throw new GenericException(e);
		}
	}

	@Override
	public UowType getType() {
		return delegate.getType();
	}

}
