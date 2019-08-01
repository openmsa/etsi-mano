package com.ubiqube.etsi.mano.controller.vnf.sol003;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.ubiqube.etsi.mano.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoLinks;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoLinksSelf;

public class Sol003Linkable implements Linkable {

	@Override
	public VnfPackagesVnfPkgInfoLinks getVnfLinks(String vnfPkgId) {
		final VnfPackagesVnfPkgInfoLinks links = new VnfPackagesVnfPkgInfoLinks();

		final VnfPackagesVnfPkgInfoLinksSelf self = new VnfPackagesVnfPkgInfoLinksSelf();
		self.setHref(linkTo(methodOn(VnfPackageSol003Api.class).vnfPackagesVnfPkgIdGet(vnfPkgId, "")).withSelfRel().getHref());
		links.self(self);

		final VnfPackagesVnfPkgInfoLinksSelf vnfd = new VnfPackagesVnfPkgInfoLinksSelf();
		vnfd.setHref(linkTo(methodOn(VnfPackageSol003Api.class).vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, "")).withSelfRel().getHref());
		links.setVnfd(vnfd);

		final VnfPackagesVnfPkgInfoLinksSelf packageContent = new VnfPackagesVnfPkgInfoLinksSelf();
		packageContent.setHref(linkTo(methodOn(VnfPackageSol003Api.class).vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId, "", null)).withSelfRel().getHref());
		links.setPackageContent(packageContent);
		return links;
	}

}
