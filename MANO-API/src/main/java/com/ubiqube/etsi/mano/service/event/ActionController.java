package com.ubiqube.etsi.mano.service.event;

import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.pkg.PackagingManager;

@Service
public class ActionController {

	private static final Logger LOG = LoggerFactory.getLogger(ActionController.class);

	private final VnfmActions vnfmActions;

	private final NfvoActions nfvoActions;

	private final PackagingManager packagingManager;

	public ActionController(final VnfmActions vnfmActions, final NfvoActions nfvoActions, final PackagingManager packagingManager) {
		super();
		this.vnfmActions = vnfmActions;
		this.nfvoActions = nfvoActions;
		this.packagingManager = packagingManager;
	}

	public void dispatch(final ActionType eventType, @NotNull final UUID objectId, final Map<String, Object> parameters) {
		switch (eventType) {
		case VNF_PKG_ONBOARD_FROM_URI:
			packagingManager.vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(objectId.toString(), (String) parameters.get("url"));
			break;
		case VNF_PKG_ONBOARD_FROM_BYTES:
			final byte[] bytes = Base64.getDecoder().decode((String) parameters.get("data"));
			packagingManager.vnfPackagesVnfPkgIdPackageContentPut(objectId.toString(), bytes);
			break;
		case VNF_INSTANTIATE:
			vnfmActions.vnfInstantiate(objectId);
			break;
		case VNF_TERMINATE:
			vnfmActions.vnfTerminate(objectId);
			break;
		case NSD_PKG_ONBOARD_FROM_BYTES:
			packagingManager.nsOnboarding(objectId);
			break;
		case NS_INSTANTIATE:
			nfvoActions.nsInstantiate(objectId);
			break;
		case NS_TERMINATE:
			nfvoActions.nsTerminate(objectId);
			break;
		case GRANT_REQUEST:
			nfvoActions.grantRequest(objectId);
			break;
		default:
			LOG.warn("Unknown event: {}", eventType);
			break;
		}
	}

}
