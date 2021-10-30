package com.ubiqube.etsi.mano.vnfm.v351.controller.vnfpm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.fc.vnfpm.VnfmPmGenericFrontController;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfpm.CreatePmJobRequest;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfpm.Link;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfpm.PerformanceReport;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfpm.PmJob;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfpm.PmJobLinks;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfpm.PmJobModifications;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@ConditionalOnMissingClass("com.ubiqube.etsi.mano.vnfm.v331.controller.vnfpm.PmJobs331Sol003Api")
@RestController
public class PmJobs351Sol003Controller implements PmJobs351Sol003Api {
	private final VnfmPmGenericFrontController vnfmPmGenericFrontController;

	public PmJobs351Sol003Controller(final VnfmPmGenericFrontController vnfmPmGenericFrontController) {
		this.vnfmPmGenericFrontController = vnfmPmGenericFrontController;
	}

	@Override
	public ResponseEntity<String> pmJobsGet(final MultiValueMap<String, String> requestParams, final String nextpageOpaqueMarker) {
		return vnfmPmGenericFrontController.search(requestParams, PmJob.class, PmJobs351Sol003Controller::makeLinks);
	}

	private static void makeLinks(final PmJob x) {
		final PmJobLinks links = new PmJobLinks();
		Link link = new Link();
		link.setHref(linkTo(methodOn(PmJobs351Sol003Api.class).pmJobsPmJobIdGet(x.getId())).withSelfRel().getHref());
		links.setSelf(link);

		link = new Link();
		link.setHref("");
		// links.setObjects(link);

		x.setLinks(links);
	}

	private static String makeSelf(final PmJob pmjob) {
		return linkTo(methodOn(PmJobs351Sol003Api.class).pmJobsPmJobIdGet(pmjob.getId())).withSelfRel().getHref();
	}

	@Override
	public ResponseEntity<Void> pmJobsPmJobIdDelete(final String pmJobId) {
		return vnfmPmGenericFrontController.deleteById(UUID.fromString(pmJobId));
	}

	@Override
	public ResponseEntity<PmJob> pmJobsPmJobIdGet(final String pmJobIdn) {
		return vnfmPmGenericFrontController.findById(UUID.fromString(pmJobIdn), PmJob.class, PmJobs351Sol003Controller::makeLinks);
	}

	@Override
	public ResponseEntity<PerformanceReport> pmJobsPmJobIdReportsReportIdGet(final String pmJobId, final String reportId) {
		return vnfmPmGenericFrontController.findReportById(pmJobId, reportId, PerformanceReport.class);
	}

	@Override
	public ResponseEntity<PmJob> pmJobsPost(@Valid final CreatePmJobRequest createPmJobRequest) {
		return vnfmPmGenericFrontController.pmJobsPost(createPmJobRequest, PmJob.class, PmJobs351Sol003Controller::makeLinks, PmJobs351Sol003Controller::makeSelf);
	}

	@Override
	public ResponseEntity<PmJobModifications> pmJobsPmJobIdPatch(final String pmJobId, final PmJobModifications pmJobModifications) {
		return vnfmPmGenericFrontController.pmJobsPmJobIdPatch(UUID.fromString(pmJobId), pmJobModifications);
	}

}
