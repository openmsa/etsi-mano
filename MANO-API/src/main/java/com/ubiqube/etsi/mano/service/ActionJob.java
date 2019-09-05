package com.ubiqube.etsi.mano.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

public class ActionJob extends QuartzJobBean {

	private static final Logger LOG = LoggerFactory.getLogger(ActionJob.class);
	private final VnfPackageRepository vnfPackageRepository;

	public ActionJob(final VnfPackageRepository vnfPackageRepository) {
		super();
		this.vnfPackageRepository = vnfPackageRepository;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		final JobDataMap jobDataMap = context.getMergedJobDataMap();
		final ActionType eventType = ActionType.valueOf(jobDataMap.getString("eventType"));
		final String objectId = jobDataMap.getString("objectId");

		dispatch(eventType, objectId, jobDataMap);
	}

	private void dispatch(final ActionType eventType, final String objectId, final JobDataMap jobDataMap) {
		switch (eventType) {
		case VNF_PKG_ONBOARD_FROM_URI:
			vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(objectId, jobDataMap.getString("url"));
			break;
		default:
			LOG.warn("Unknown event: {}", eventType);
			break;
		}
	}

	private void vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(final String vnfPkgId, final String url) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		vnfPkgInfo.setOnboardingState(OnboardingStateEnum.PROCESSING);
		vnfPackageRepository.save(vnfPkgInfo);
		LOG.info("Async. Download of {}", url);
		final InputStream content = getUrlContent(url);
		vnfPackageRepository.storeBinary(vnfPkgId, content, "vnfd");
		vnfPkgInfo.setOnboardingState(OnboardingStateEnum.ONBOARDED);
		vnfPackageRepository.save(vnfPkgInfo);
	}

	private static InputStream getUrlContent(final String uri) {
		URL url;
		try {
			url = new URL(uri);
			return (InputStream) url.getContent();
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}
}
