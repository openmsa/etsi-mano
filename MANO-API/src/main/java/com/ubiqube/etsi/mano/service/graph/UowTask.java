package com.ubiqube.etsi.mano.service.graph;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VirtualLinkInfo;
import com.ubiqube.etsi.mano.dao.mano.VirtualStorageInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmResourceChanges;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class UowTask extends Task<UnitOfWork, String> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(UowTask.class);

	private final VimConnectionInformation vimConnectionInformation;

	private final transient Vim vim;

	private final UnitOfWork uaow;

	private final Map<String, String> context = new HashMap<>();
	private VnfInstance vnfInstance;
	private VnfLcmOpOccs lcmOpOccs;

	public UowTask(final VimConnectionInformation vimConnectionInformation, final Vim vim, final UnitOfWork uaow) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		this.uaow = uaow;
	}

	@Override
	public String execute() {
		FailureDetails failureDetails = null;
		RuntimeException eRoot = null;
		Optional<String> res = Optional.empty();
		try {
			res = Optional.ofNullable(uaow.exec(vimConnectionInformation, vim, context));
			res.ifPresent(x -> context.put(uaow.getName(), x));
		} catch (final RuntimeException e) {
			eRoot = e;
			String instance = null;
			try {
				instance = InetAddress.getLocalHost().getCanonicalHostName();
			} catch (final UnknownHostException e1) {
				LOG.debug("Could not determine this hostname.", e1);
			}
			failureDetails = new FailureDetails();
			failureDetails.setDetail(e.getMessage());
			failureDetails.setStatus(500L);
			failureDetails.setTitle("Task " + uaow.getName() + " Failed.");
			failureDetails.setType("about:blank");
			failureDetails.setInstance(instance);
			lcmOpOccs.setError(failureDetails);
		}
		final VnfInstantiatedInfo vnfInstantiated = vnfInstance.getInstantiatedVnfInfo();
		final ResourceHandleEntity resource = this.uaow.getResourceHandleEntity();
		resource.setEndTime(new Date());
		resource.setResourceId(res.get());
		addResource(vnfInstantiated, res.get());
		final VnfLcmResourceChanges resourceChanged = lcmOpOccs.getResourceChanges();
		// resourceChanged.setAffectedVirtualLinks(affectedVirtualLinks);
		if (eRoot != null) {
			throw eRoot;
		}
		return res.get();
	}

	private void addResource(final VnfInstantiatedInfo vnfInstantiated, final String resourceId) {
		switch (uaow.getType()) {
		case VL:
			vnfInstantiated.addVirtualLinkResourceInfoItem(createVl());
			break;
		case COMPUTE:
			vnfInstantiated.addVnfcResourceInfoItem(createCompute());
			break;
		case CP:
			//
			break;
		case VSTORAGE:
			vnfInstantiated.addVirtualStorageResourceInfoItem(createVStorage(resourceId));
			break;
		default:
			break;
		}
	}

	private VirtualStorageInfo createVStorage(final String resourceId) {
		final VirtualStorageInfo vsri = new VirtualStorageInfo();
		final ResourceHandleEntity storageResource = new ResourceHandleEntity();
		storageResource.setResourceId(resourceId);
		vsri.setStorageResource(storageResource);
		return vsri;
	}

	private VirtualLinkInfo createVl() {
		// TODO Auto-generated method stub
		return null;
	}

	private VnfInstantiedCompute createCompute() {
		// TODO Auto-generated method stub
		return null;
	}

}
