package com.ubiqube.etsi.mano.service.event.jms;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.event.NfvoActions;
import com.ubiqube.etsi.mano.service.event.VnfmActions;
import com.ubiqube.etsi.mano.service.pkg.PackagingManager;

@Service
public class ActionsController {

	private static final Logger LOG = LoggerFactory.getLogger(ActionsController.class);
	private final VnfmActions vnfmActions;

	private final NfvoActions nfvoActions;

	private final PackagingManager packagingManager;

	public ActionsController(final VnfmActions vnfmActions, final NfvoActions nfvoActions, final PackagingManager packagingManager) {
		super();
		this.vnfmActions = vnfmActions;
		this.nfvoActions = nfvoActions;
		this.packagingManager = packagingManager;
	}

	@JmsListener(destination = "system.actions")
	public void onEvent(final ActionMessage ev) {
		LOG.info("Receiving Action: {}", ev);
		switch (ev.getActionType()) {
		case VNF_PKG_ONBOARD_FROM_URI:
			packagingManager.vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(ev.getObjectId(), (String) ev.getParameters().get("url"));
			break;
		case VNF_PKG_ONBOARD_FROM_BYTES:
			final byte[] bytes = Base64.getDecoder().decode((String) ev.getParameters().get("data"));
			packagingManager.vnfPackagesVnfPkgIdPackageContentPut(ev.getObjectId(), bytes);
			break;
		case VNF_INSTANTIATE:
			vnfmActions.vnfInstantiate(ev.getObjectId());
			break;
		case NS_INSTANTIATE:
			nfvoActions.nsInstantiate(ev.getObjectId());
			break;
		case NS_TERMINATE:
			nfvoActions.nsTerminate(ev.getObjectId());
			break;
		default:
			LOG.warn("Unknown event: {}", ev.getObjectId());
			break;
		}
	}

}
