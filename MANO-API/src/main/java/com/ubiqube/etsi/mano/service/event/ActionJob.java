package com.ubiqube.etsi.mano.service.event;

import javax.annotation.Nonnull;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * this class handle job reception.
 *
 * TODO: I keep Package here for the momment.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Transactional
public class ActionJob extends QuartzJobBean {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(ActionJob.class);

	private final VnfmActions vnfmActions;
	private final NfvoActions nfvoActions;

	private final PackagingManager packagingManager;

	public ActionJob(final VnfmActions _vnfmActions, final NfvoActions _nfvoActions, final PackagingManager _packagingManager) {
		super();
		vnfmActions = _vnfmActions;
		nfvoActions = _nfvoActions;
		packagingManager = _packagingManager;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		final JobDataMap jobDataMap = context.getMergedJobDataMap();
		final ActionType eventType = ActionType.valueOf(jobDataMap.getString("eventType"));
		final String objectId = jobDataMap.getString("objectId");

		dispatch(eventType, objectId, jobDataMap);
	}

	private void dispatch(final ActionType eventType, @Nonnull final String objectId, final JobDataMap jobDataMap) {
		switch (eventType) {
		case VNF_PKG_ONBOARD_FROM_URI:
			packagingManager.vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(objectId, jobDataMap.getString("url"));
			break;
		case VNF_PKG_ONBOARD_FROM_BYTES:
			packagingManager.vnfPackagesVnfPkgIdPackageContentPut(objectId, (byte[]) jobDataMap.get("data"));
			break;
		case VNF_INSTANTIATE:
			vnfmActions.vnfInstantiate(objectId);
			break;
		case NS_INSTANTIATE:
			nfvoActions.nsInstantiate(objectId);
			break;
		case NS_TERMINATE:
			nfvoActions.nsTerminate(objectId);
			break;
		default:
			LOG.warn("Unknown event: {}", eventType);
			break;
		}
	}

}
